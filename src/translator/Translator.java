package translator;

import arm.*;
import arm.PredefinedFunctions.PredefinedFunction;
import expr_nodes.*;
import node.Node;
import semantics.Function;
import semantics.SymbolTable;
import stat_nodes.*;
import stat_nodes.AssignmentEqNode.Operator;
import type.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static arm.Enumerables.Condition.*;
import static arm.Enumerables.Flags;

/**
 * This class is used to translate WACC code into machine language (Assembly code) used to execute the commands
 * of the user.
 */

public class Translator {

    private List<Instruction> list = new ArrayList<>();
    private FileBegin fileBegin = new FileBegin();
    private PredefinedFunctions predefinedFunctions = new PredefinedFunctions(list, fileBegin);
    private Stack stack;

    private final int MAX_DECLARATION_SIZE = 1024;

    private Register r0 = new Register(0);
    private Register r1 = new Register(1);
    private Register r10 = new Register(10);
    private Register r11 = new Register(11);
    private Register register = new Register(4);
    private Register sp = new Register(13);
    private Register pc = new Register(15);

    private boolean translatingAssignLhs = false;

    private Function currentFunction;
    private boolean isReturning = false;

    // Used to distinguish whether one is visiting a newpair in declaration or fst/snd.
    private boolean fstOrSnd = false;
    private boolean fstOrSndRightHandSide = false;

    // Used when translating Declarations.
    private boolean isDeclaring = false;

    private boolean isBreak = false;
    private Label breakLabel = new Label(false);
    private Label trueLabel = new Label(false);

    // Used when there are more arguments than registers and arguments need to be obtained from the stack.
    private int numberOfArgumentsOnStack;

    public Translator() {
        list.add(fileBegin);
    }

    public void translateFunction(Function function) {

        list.add(new Label(function.getId()));
        list.add(new FunctionBegin());

        currentFunction = function;

        stack = new Stack(currentFunction.getScope());

        int stackSize = stack.getSizeInBytes();

        // handles the case that a program needs to reserve stack space for more than 1024 bits.
        while (stackSize > MAX_DECLARATION_SIZE) {
            list.add(new SUB(sp, sp, MAX_DECLARATION_SIZE));
            stackSize -= MAX_DECLARATION_SIZE;
        }

        if (stack.getSizeInBytes() != 0) {
            list.add(new SUB(sp, sp, stackSize));
        }

        function.getBody().translate(this);

        stackSize = stack.getSizeInBytes();

        if (stack.getSizeInBytes() != 0) {
            list.add(new ADD(sp, sp, stack.getSizeInBytes() % MAX_DECLARATION_SIZE));
        }

        while (stackSize > MAX_DECLARATION_SIZE) {
            list.add(new ADD(sp, sp, MAX_DECLARATION_SIZE));
            stackSize -= MAX_DECLARATION_SIZE;
        }

        if (function.getId().equals("main")) {
            list.add(new LDR(r0, 0));
        }

        list.add(new POP(pc));
        list.add(new FunctionEnd());
    }

    public void translateCallNode(CallNode callNode) {

        int argCount = callNode.getArgCount();
        int totalArgByteSize = 0;

        // For each param in reverse order
        for (int i = 0; i < argCount; i++) {
            ExprNode currentArg = callNode.getArg(i);
            currentArg.translate(this);

            int currentArgSize = 0;

            if (currentArg instanceof BoolNode) {
                currentArgSize += 1;
            } else if (currentArg instanceof  CharNode) {
                currentArgSize += 1;
            } else if (currentArg instanceof IntNode) {
                currentArgSize += 4;
            } else if (currentArg instanceof ArrayNode) {
                currentArgSize += 4;
            }

            list.add(new STR(register, sp, -currentArgSize, currentArg.getType()));

            totalArgByteSize += currentArgSize;

            Stack tempStack = stack;
            stack = new Stack(callNode.getFunction().getScope());
            stack.setParentStack(tempStack);
        }

        list.add(new BL(callNode.getFunction().getId()));

        if (argCount > 0) {
            list.add(new ADD(sp, sp, new SecondOperand(totalArgByteSize, false)));
        }

        list.add(new MOV(register, new SecondOperand(r0)));
        list.add(new STR(register, sp));
    }

    public void translateExit(ExitNode exitNode) {

        exitNode.getExpr().translate(this);

        list.add(new MOV(r0, new SecondOperand(register)));

        list.add(new BL("exit"));
    }

    public void translateInt(IntNode intNode) {

        if (isDeclaring) {
            stack.add(intNode.getName(), intNode.getType());
        }

        list.add(new LDR(register, intNode.getValue()));

        if (isDeclaring) {
            list.add(new STR(register, sp, stack.getOffsetOfVariable(intNode.getName())));
        }
    }

    public void translateChar(CharNode charNode) {

        if (isDeclaring) {
            stack.add(charNode.getName(), charNode.getType());
        }

        list.add(new MOV(register, new SecondOperand(charNode.getValue(), true)));

        if (isDeclaring) {
            list.add(new STR(register, sp, stack.getOffsetOfVariable(charNode.getName()), charNode.getType()));
        }
    }

    public void translateBool(BoolNode boolNode) {

        if (isDeclaring) {
            stack.add(boolNode.getName(), boolNode.getType());
        }

        list.add(new MOV(register, boolNode.getValue()));

        if (isDeclaring) {
            list.add(new STR(register, sp, stack.getOffsetOfVariable(boolNode.getName()), boolNode.getType()));
        }
    }

    public void translateString(ArrayNode stringNode) {

        if (isDeclaring) {
            stack.add(stringNode.getName(), stringNode.getType());
        }

        Message string = new Message(stringNode.getValue(), true);

        fileBegin.addMessage(string);

        list.add(new LDR(register, fileBegin.getLabel(string)));

        if (isDeclaring) {
            list.add(new STR(register, sp, stack.getOffsetOfVariable(stringNode.getName())));
        }
    }

    public void translateComposition(CompositionNode compositionNode) {

        List<StatNode> statements = compositionNode.statements;

        for (StatNode statNode : statements) {
            statNode.translate(this);
        }
    }

    public void translateReturn(ReturnNode returnNode) {

        returnNode.getExpr().translate(this);

        list.add(new MOV(r0, new SecondOperand(register)));

        isReturning = true;
        int numElemsInScope = stack.getSizeInBytes() - currentFunction.totalParameterSizeInBytes();

        if (numElemsInScope != 0) {
            LinkedList<SecondOperand> operands = separateIntoValidSizes(numElemsInScope);
            for (SecondOperand secondOperand : operands) {
                list.add(new ADD(sp, sp, secondOperand));
            }
        }

        list.add(new POP(pc));
    }

    public void translateScopeNode(ScopeNode scopeNode) {

        Stack temporaryStack = stack;
        stack = scopeNode.getScope();
        stack.setParentStack(temporaryStack);

        int numElemsInScope = stack.getSizeInBytes();

        // Make new numberOfImmediateValuesediate operand 2
        LinkedList<SecondOperand> operands = separateIntoValidSizes(numElemsInScope);

        // Subtract from stack if there are local variables
        if (numElemsInScope != 0) {
            for (SecondOperand secondOperand : operands) {
                list.add(new SUB(sp, sp, secondOperand));
            }
        }

        // Translate scope's body
        scopeNode.getStat().translate(this);

        // Add to stack if you subtracted
        if (!isReturning && numElemsInScope != 0) {
            for (SecondOperand secondOperand : operands) {
                list.add(new ADD(sp, sp, secondOperand));
            }
        }

        stack = temporaryStack;

        if (isReturning) {
            list.add(new POP(pc));
        }

        isReturning = false;
    }

    public void translateDeclaration(stat_nodes.DeclarationNode declarationNode) {

        isDeclaring = true;

        if (declarationNode.getNode() instanceof PairElemNode) {

            // This statement is hit in the cases that are similar to the following example:
            // int f = fst p, where p = (int, int)

            fstOrSnd = true;
            fstOrSndRightHandSide = true;

            translatePairElem((PairElemNode) declarationNode.getNode());

            fstOrSndRightHandSide = false;

        } else if (declarationNode.getType() instanceof ArrayType && ((ArrayNode) declarationNode.getNode()).isPureString()) {
            translateString((ArrayNode) declarationNode.getNode());
        } else {
            declarationNode.getNode().translate(this);
        }

        isDeclaring = false;
    }

    public void translatePrint(PrintNode printNode) {

        if (printNode.getExpr() instanceof PairLiteralNode) {
            // Takes care of the case in which we are asked to print null.
            list.add(new LDR(register, 0));
        } else {
            printNode.getExpr().translate(this);
        }

        list.add(new MOV(r0, new SecondOperand(register)));

        Type type = printNode.getType();

        if (type instanceof BoolType) {

            predefinedFunctions.add(PredefinedFunction.P_PRINT_BOOL);

        } else if (type.equals(new ArrayType(new CharType()))) {

            predefinedFunctions.add(PredefinedFunction.P_PRINT_STRING);

        } else if (type instanceof IntegerType) {

            predefinedFunctions.add(PredefinedFunction.P_PRINT_INT);

        } else if (type instanceof CharType) {

            list.add(new BL("putchar"));

        } else {

            predefinedFunctions.add(PredefinedFunction.P_PRINT_REFERENCE);

        }

    }

    public void translatePrintln(PrintlnNode printlnNode) {

        ExprNode exprNode = printlnNode.getExpr();

        PrintNode printNode = new PrintNode(exprNode);

        this.translatePrint(printNode);

        predefinedFunctions.add(PredefinedFunction.P_PRINTLN);
    }

    public void translateIf(IfNode ifNode) {

        ifNode.getCondition().translate(this);

        Label trueLabel = new Label(false);

        Label falseLabel = new Label(false);

        list.add(new CMP(register, 0));

        list.add(new BL(trueLabel.getLabel()).withCondition(EQ));

        Stack globalStack = stack;
        stack = new Stack(ifNode.getIfSymbolTable());
        stack.setParentStack(globalStack);

        ifNode.getBodyWhenTrue().translate(this);

        list.add(new BL(falseLabel.getLabel()).withoutLabel());

        list.add(trueLabel);

        stack = new Stack(ifNode.getElseSymbolTable());
        stack.setParentStack(globalStack);

        ifNode.getBodyWhenFalse().translate(this);

        list.add(falseLabel);

        stack = globalStack;
        stack.setParentStack(null);
    }

    public void translateIdentifier(IdentifierNode identifierNode) {

        if (isDeclaring) {
            stack.add(identifierNode.getAssigneeName(), identifierNode.getType());
        }

        String name = identifierNode.getName();
        int offset = stack.getOffsetOfVariable(name);
        Type type = stack.getTypeOfVariable(name);

        list.add(new LDR(register, sp, offset, type));

        if (isDeclaring) {
            list.add(new STR(register, sp, stack.getOffsetOfVariable(identifierNode.getAssigneeName()), identifierNode.getType()));
        }
    }

    public List<Instruction> getList() {

        FileEnd fileEnd = new FileEnd(predefinedFunctions);
        list.add(fileEnd);
        return list;
    }

    public void translateArrayElemNode(ArrayElemNode arrayElemNode) {

        boolean isDeclaringLocal = isDeclaring;

        if (isDeclaring) {
            isDeclaring = false;
        }

        IdentifierNode identifierNode = arrayElemNode.getIdentifierNode();

        String name = identifierNode.getName();

        // Add the location in the stack where the array starts from to register.
        list.add(new ADD(register, sp, stack.getOffsetOfVariable(name)));
        
        for (int i = 0; i < arrayElemNode.getIndicesSize(); i++) {

            // Load the index in register + 1
            increment();

            arrayElemNode.getIndexAt(i).translate(this);

            decrement();

            // Load the length of the array into register.
            // Since the length of the array is stored in the first position of the array on the Stack,
            // then we just load [register] (length) into register.
            list.add(new LDR(register, register, 0));

            // Move the index we want to use to access into register 0.
            list.add(new MOV(r0, new SecondOperand(register.next())));

            // Move the length of the array in register 1.
            list.add(new MOV(r1, new SecondOperand(register)));

            predefinedFunctions.add(PredefinedFunction.P_CHECK_ARRAY_BOUNDS);

            // Increase the size of the array by four?!
            list.add(new ADD(register, register, new SecondOperand(4, false)));

            // I do not understand what do these lines do?!
            if (arrayElemNode.getType() instanceof CharType) {

                list.add(new ADD(register, register, new SecondOperand(register.next())));

            } else {
                list.add(new ADD(register, register, new SecondOperand(register.next(), SecondOperand.Shift.LSL, 2)));

            }

            if (!translatingAssignLhs && (i == (arrayElemNode.getIndicesSize() - 1))) {
                list.add(new LDR(register, register, 0));

            }
        }

        if (isDeclaringLocal) {
            isDeclaring = true;
        }
    }

    public void translateArrayLiteralNode(ArrayNode arrayNode) {

        boolean isDeclaringLocal = isDeclaring;

        if (isDeclaring) {
            isDeclaring = false;
        }

        stack.add(arrayNode.getName(), arrayNode.getType());

        if (arrayNode.isPureString()) {

            Message message = new Message(arrayNode.toString(), true);

            fileBegin.addMessage(message);

            list.add(new LDR(register, fileBegin.getLabel(message)));

        } else {

            list.add(new LDR(r0, arrayNode.getSizeInBytes()));

            list.add(new BL("malloc"));

            list.add(new MOV(register, new SecondOperand(r0)));

            int offsetOfCurrentElement = 0;

            for (int i = 0; i < arrayNode.getArrayLength(); i++) {

                ExprNode element = arrayNode.getElementAt(i);

                increment();

                element.translate(this);

                decrement();

                // handles the case of the empty array.
                if (offsetOfCurrentElement == 0) {

                    offsetOfCurrentElement = 4;

                } else {

                    if (element instanceof BoolNode || element instanceof CharNode) {

                        offsetOfCurrentElement += 1;

                    } else {

                        offsetOfCurrentElement += 4;
                    }
                }

                list.add(new STR(register.next(), register, offsetOfCurrentElement, element.getType()));

            }

            increment();

            IntNode intNode = new IntNode();

            intNode.setValue(arrayNode.getArrayLength());

            intNode.translate(this);

            decrement();

            list.add(new STR(register.next(), register, 0));

            list.add(new STR(register, sp, stack.getOffsetOfVariable(arrayNode.getName())));
        }

        if (isDeclaringLocal) {
            isDeclaring = true;
        }
    }

    public void translateBinaryOperator(BinaryOperNode binaryOperNode) {

        boolean isDeclaringLocal = isDeclaring;
        int numberOfImmediateValues = 0;

        ExprNode lhs = binaryOperNode.getLhs();
        ExprNode rhs = binaryOperNode.getRhs();

        // used to differentiate expressions from immediate values.
        if (lhs instanceof LiteralNode) {
            numberOfImmediateValues++;
        }

        if (rhs instanceof LiteralNode) {
            numberOfImmediateValues++;
        }

        if (isDeclaring) {
            isDeclaring = false;
        }

        lhs.translate(this);

        this.increment();
        rhs.translate(this);
        this.decrement();

        if (isDeclaringLocal) {
            isDeclaring = true;
        }

        Register destination = register;
        Register operandOneRegister;
        Register operandTwoRegister;

        if (numberOfArgumentsOnStack > 0) {
            list.add(new POP(r11));

            numberOfArgumentsOnStack--;

            operandOneRegister = r11;
            operandTwoRegister = register;
        } else {
            operandOneRegister = register;
            operandTwoRegister = register.next();
        }


        switch (binaryOperNode.getOperator()) {

            case DIV:
                list.add(new MOV(r0, new SecondOperand(operandOneRegister)));
                list.add(new MOV(r1, new SecondOperand(operandTwoRegister)));
                predefinedFunctions.add(PredefinedFunction.P_CHECK_DIVIDE_BY_ZERO);
                list.add(new BL("__aeabi_idiv"));
                list.add(new MOV(destination, new SecondOperand(r0)));
                break;

            case EQ:
                list.add(new CMP(operandOneRegister, new SecondOperand(operandTwoRegister)));
                list.add(new MOV(destination, 1).withCondition(EQ));
                list.add(new MOV(destination, 0).withCondition(NE));
                break;

            case GTE:
                list.add(new CMP(operandOneRegister, new SecondOperand(operandTwoRegister)));
                list.add(new MOV(destination, 1).withCondition(GE));
                list.add(new MOV(destination, 0).withCondition(LT));
                break;

            case GT:
                list.add(new CMP(operandOneRegister, new SecondOperand(operandTwoRegister)));
                list.add(new MOV(destination, 1).withCondition(GT));
                list.add(new MOV(destination, 0).withCondition(LE));
                break;

            case LTE:
                list.add(new CMP(operandOneRegister, new SecondOperand(operandTwoRegister)));
                list.add(new MOV(destination, 1).withCondition(LE));
                list.add(new MOV(destination, 0).withCondition(GT));
                break;

            case LT:
                list.add(new CMP(operandOneRegister, new SecondOperand(operandTwoRegister)));
                list.add(new MOV(destination, 1).withCondition(LT));
                list.add(new MOV(destination, 0).withCondition(GE));
                break;

            case AND:
                list.add(new AND(destination, operandOneRegister, new SecondOperand(operandTwoRegister)));
                break;

            case OR:
                list.add(new ORR(destination, operandOneRegister, new SecondOperand(operandTwoRegister)));
                break;

            case MINUS:
                if (numberOfImmediateValues == 1) {
                    list.add(new RSB(destination, operandTwoRegister, new SecondOperand(operandOneRegister)).withFlags(Flags.S));
                } else {
                    list.add(new SUB(destination, operandOneRegister, new SecondOperand(operandTwoRegister)).withFlags(Flags.S));
                }
                predefinedFunctions.add(PredefinedFunction.P_THROW_OVERFLOW_ERROR);
                break;

            case MOD:
                list.add(new MOV(r0, new SecondOperand(operandOneRegister)));
                list.add(new MOV(r1, new SecondOperand(operandTwoRegister)));
                predefinedFunctions.add(PredefinedFunction.P_CHECK_DIVIDE_BY_ZERO);
                list.add(new BL("__aeabi_idivmod"));
                list.add(new MOV(destination, new SecondOperand(r1)));
                break;

            case MUL:
                if (numberOfArgumentsOnStack > 0) {
                    list.add(new SMULL(r10, r11, r11, r10));
                    list.add(new CMP(r11, new SecondOperand(r10, SecondOperand.Shift.ASR, 31)));
                } else {
                    list.add(new SMULL(register, register.next(), register, register.next()));
                    list.add(new CMP(register.next(), new SecondOperand(register, SecondOperand.Shift.ASR, 31)));
                }
                predefinedFunctions.add(PredefinedFunction.P_THROW_OVERFLOW_ERROR_NE);
                break;

            case NEQ:
                list.add(new CMP(operandOneRegister, new SecondOperand(operandTwoRegister)));
                list.add(new MOV(destination, 1).withCondition(NE));
                list.add(new MOV(destination, 0).withCondition(EQ));
                break;

            case PLUS:
                list.add(new ADD(destination, operandOneRegister, new SecondOperand(operandTwoRegister), Flags.S));
                predefinedFunctions.add(PredefinedFunction.P_THROW_OVERFLOW_ERROR);
                break;

            default:
                break;
        }

        doWeWantToStoreVariableWhenEvaluatingOperators(binaryOperNode);
    }

    private void doWeWantToStoreVariableWhenEvaluatingOperators(Node operator) {

        if (isDeclaring) {
            stack.add(operator.getName(), operator.getType());
            list.add(new STR(register, sp, stack.getOffsetOfVariable(operator.getName()), operator.getType()));
        }
    }

    public void translateUnaryOpNode(UnaryOperNode unaryOperNode) {

        boolean isDeclaringLocal = isDeclaring;

        if (isDeclaring) {
            isDeclaring = false;
        }

        ExprNode exprNode = unaryOperNode.getExpr();

        exprNode.translate(this);

        if (isDeclaringLocal) {
            isDeclaring = true;
        }

        switch (unaryOperNode.getOperator()) {
            case LEN:
                list.add(new LDR(register, sp, stack.getOffsetOfVariable(exprNode.getName())));
                list.add(new LDR(register, register, 0));
                break;
            case NOT:
                list.add(new EOR(register, register, new SecondOperand(1, false)));
                break;
            case MINUS:
                list.add(new RSB(register, register, new SecondOperand(0, false)).withFlags(Flags.S));
                predefinedFunctions.add(PredefinedFunction.P_THROW_OVERFLOW_ERROR);
                break;
            default:
                break;
        }

        doWeWantToStoreVariableWhenEvaluatingOperators(unaryOperNode);
    }

    private void increment() {
        if (register.equals(r10)) {
            list.add(new PUSH(r10));
            numberOfArgumentsOnStack++;
        } else {
            register = register.next();
        }
    }

    private void decrement() {
        if (numberOfArgumentsOnStack == 0) {
            register = register.previous();
        }
    }

    public void translateAssignment(AssignmentNode assignmentNode) {

        if (assignmentNode.getRhs() instanceof PairElemNode) {
            fstOrSnd = true;
            fstOrSndRightHandSide = true;
        }

        assignmentNode.getRhs().translate(this);

        fstOrSnd = false;
        fstOrSndRightHandSide = false;

        increment();

        if (assignmentNode.getLhs() instanceof PairElemNode) {
            fstOrSnd = true;
            assignmentNode.getLhs().translate(this);
            fstOrSnd = false;
        } else if (assignmentNode.getLhs() instanceof ArrayElemNode) {
            translatingAssignLhs = true;
            assignmentNode.getLhs().translate(this);
            translatingAssignLhs = false;
        }

        decrement();

        ExprNode lhs = assignmentNode.getLhs();

        if (lhs instanceof ArrayElemNode || lhs instanceof PairElemNode) {
            list.add(new STR(register, register.next(), 0, lhs.getType()));
        } else {
            list.add(new STR(register, sp, stack.getOffsetOfVariable(lhs.getName()), lhs.getType()));
        }
    }

    public void translateWhile(WhileNode whileNode) {

        trueLabel = new Label (false);

        Label falseLabel = new Label(false);

        list.add(new BL(trueLabel.getLabel()).withoutLabel());

        list.add(falseLabel);

        // creates a new scope for the loop body.
        Stack globalStack = stack;
        stack = new Stack(whileNode.getSymbolTable());
        stack.setParentStack(globalStack);

        whileNode.getBody().translate(this);

        stack = globalStack;
        stack.setParentStack(null);

        list.add(trueLabel);

        whileNode.getCondition().translate(this);

        list.add(new CMP(register, 1));

        list.add(new BL(falseLabel.getLabel()).withCondition(EQ));

        if (isBreak) {
            list.add(breakLabel);
            isBreak = false;
        }
    }

    private LinkedList<SecondOperand> separateIntoValidSizes(int numElemsInScope) {
        LinkedList<SecondOperand> splitValues = new LinkedList<>();

        while (numElemsInScope > MAX_DECLARATION_SIZE) {
            numElemsInScope -= MAX_DECLARATION_SIZE;
            splitValues.add(new SecondOperand(MAX_DECLARATION_SIZE, false));
        }

        splitValues.add(new SecondOperand(numElemsInScope, false));

        return splitValues;
    }

    public void translatePairElem(PairElemNode pairElemNode) {
        if (fstOrSnd) {

            list.add(new LDR(register, sp, stack.getOffsetOfVariable(pairElemNode.getPairName())));

            list.add(new MOV(r0, new SecondOperand(register)));

            predefinedFunctions.add(PredefinedFunction.P_CHECK_NULL_POINTER);

            if (fstOrSndRightHandSide) {
                if (pairElemNode.isFirst()) {
                    list.add(new LDR(register, register, 0));
                } else {
                    list.add(new LDR(register, register, 4));
                }
            }

            if (fstOrSndRightHandSide) {
                list.add(new LDR(register, register, 0, pairElemNode.getType()));
            } else {
                if (pairElemNode.isFirst()) {
                    list.add(new LDR(register, register, 0));
                } else {
                    list.add(new LDR(register, register, 4));
                }
            }

            if (isDeclaring && fstOrSndRightHandSide) {
                stack.add(pairElemNode.getName(), pairElemNode.getType());

                list.add(new STR(register, sp, stack.getOffsetOfVariable(pairElemNode.getName()), pairElemNode.getType()));
            }

            fstOrSnd = false;
        } else {
            pairElemNode.getValue().translate(this);
        }
    }

    public void translatePair(stat_nodes.PairNode pairNode) {

        boolean isDeclaringLocal = isDeclaring;

        stack.add(pairNode.getName(), pairNode.getType());

        if (pairNode.isNull()) {
            if (pairNode.getReferenceName() == null) {
                list.add(new LDR(register, 0));
                // This needed to be changed because we want to store 0
                // in the stack at the location of the identifier.
                list.add(new STR(register, sp, stack.getOffsetOfVariable(pairNode.getName())));
                return;
            } else {
                if (pairNode.isReferencing()) {
                    list.add(new LDR(register, sp, stack.getOffsetOfVariable(pairNode.getReferenceName())));
                    list.add(new STR(register, sp, stack.getOffsetOfVariable(pairNode.getName())));
                } else {
                    list.add(new LDR(register, sp, stack.getOffsetOfVariable(pairNode.getReferenceName())));
                    fstOrSnd = true;
                    pairNode.getRhs().translate(this);
                    fstOrSnd = false;
                    list.add(new STR(register, sp, stack.getOffsetOfVariable(pairNode.getName())));
                }
                return;
            }
        }

        // Allocates memory for the two pointers to the elements of the pair
        list.add(new LDR(r0, 8));
        list.add(new BL("malloc"));

        list.add(new MOV(register, new SecondOperand(r0)));

        increment();

        if (isDeclaring) {
            isDeclaring = false;
        }

        pairNode.getLhs().translate(this);

        decrement();

        // Allocates memory for the first element of the pair
        ExprNode firstElem = pairNode.getLhs();
        int firstElemSize = firstElem.getSize();
        list.add(new LDR(r0, firstElemSize));
        list.add(new BL("malloc"));

        list.add(new STR(register.next(), r0, 0, firstElem.getType()));

        list.add(new STR(r0, register, 0));

        increment();

        pairNode.getRhs().translate(this);

        if (isDeclaringLocal) {
            isDeclaring = true;
        }

        decrement();

        // Allocates memory for the second element in the pair
        ExprNode secondElem = pairNode.getRhs();
        int secondElemSize = secondElem.getSize();
        list.add(new LDR(r0, secondElemSize));
        list.add(new BL("malloc"));

        list.add(new STR(register.next(), r0, 0, secondElem.getType()));

        list.add(new STR(r0, register, 4));

        list.add(new STR(register, sp, stack.getOffsetOfVariable(pairNode.getName())));
    }

    public void translatePairLiteral() {
        list.add(new LDR(register, 0));
    }

    public void translateRead(ReadNode readNode) {

        // The idea behind the additions is that if we have read identifier
        // all we need is to get the offset of the identifier.
        // If we have read fst identifier, however, we would need to
        // visit the expression that the read node holds.

        String name = readNode.getName();

        if (name != null) {
            int offset = stack.getOffsetOfVariable(readNode.getName());
            list.add(new ADD(register, sp, offset));
        } else {
        // This is potentially needed in case when the expression is a fst or snd.
            fstOrSnd = true;
            readNode.getExpr().translate(this);
            fstOrSnd = false;
        //
        }

        list.add(new MOV(r0, new SecondOperand(register)));

        if (readNode.getType().equals(new CharType())) {
            predefinedFunctions.add(PredefinedFunction.P_READ_CHAR);
        } else {
            predefinedFunctions.add(PredefinedFunction.P_READ_INT);
        }

    }

    public void translateFree(FreeNode freeNode) {
        ExprNode expression = freeNode.getExpr();

        // Translate the expression.
        expression.translate(this);

        // Load the translated and evaluated expression into r0.
        list.add(new MOV(r0, new SecondOperand(register)));

        // Call the predefined function for freeing pairs.
        if (expression.getType() instanceof PairType) {
            predefinedFunctions.add(PredefinedFunction.P_FREE_PAIR);
        }
    }

    public void translateBegin(BeginNode beginNode) {
        // Creates a new stack to act as the inner stack
        Stack temp = stack;

        SymbolTable scope = beginNode.getSymbolTable();

        stack = new Stack(scope);

        stack.setParentStack(temp);

        list.add(new SUB(sp, sp, stack.getSizeInBytes()));

        beginNode.getStatNode().translate(this);

        list.add(new ADD(sp, sp, stack.getSizeInBytes()));

        stack = temp;
    }

    public void translateDoWhile(DoWhileNode doWhileNode) {

        trueLabel = new Label(false);

        Label falseLabel = new Label(false);

        list.add(new BL(falseLabel.getLabel()).withoutLabel());

        list.add(falseLabel);

        Stack globalStack = stack;
        stack = new Stack(doWhileNode.getSymbolTable());
        stack.setParentStack(globalStack);

        doWhileNode.getBody().translate(this);

        list.add(new BL(trueLabel.getLabel()).withoutLabel());

        stack = globalStack;
        stack.setParentStack(null);

        list.add(trueLabel);

        doWhileNode.getCondition().translate(this);

        list.add(new CMP(register, 1));

        list.add(new BL(falseLabel.getLabel()).withCondition(EQ));

        if (isBreak) {
            list.add(breakLabel);
            isBreak = false;
        }
    }

    public void translateFor(ForNode forNode) {

        trueLabel = new Label(false);

        Label falseLabel = new Label(false);

        list.add(new SUB(sp, sp, stack.getSizeInBytes()));

        forNode.getDeclaration().translate(this);

        list.add(new BL(trueLabel.getLabel()).withoutLabel());

        list.add(falseLabel);

        Stack globalStack = stack;
        stack = new Stack(forNode.getSymbolTable());
        stack.setParentStack(globalStack);

        forNode.getBody().translate(this);

        // reconnects the local scope with the parent because is lost during body's translation.
        stack.setParentStack(globalStack);

        forNode.getAssignment().translate(this);

        list.add(new BL(trueLabel.getLabel()).withoutLabel());

        stack = globalStack;
        stack.setParentStack(null);

        list.add(trueLabel);

        forNode.getCondition().translate(this);

        list.add(new CMP(register, 1));

        list.add(new BL(falseLabel.getLabel()).withCondition(EQ));

        if (isBreak) {
            list.add(breakLabel);
            isBreak = false;
        }

        list.add(new ADD(sp, sp, stack.getSizeInBytes()));
    }

    public void translateBreak() {
        isBreak = true;
        list.add(new BL(breakLabel.getLabel()).withoutLabel());
    }

    public void translateContinue() {
        list.add(new BL(trueLabel.getLabel()).withoutLabel());
    }

    public void translateAssignmentEq(AssignmentEqNode assignmentEqNode) {

        ExprNode rhs = assignmentEqNode.getRhs();
        if (rhs != null) {
            assignmentEqNode.getRhs().translate(this);
        }

        ExprNode lhs = assignmentEqNode.getLhs();
        Operator operator = assignmentEqNode.getOperator();
        BinaryOperNode binaryOperNode = new BinaryOperNode(lhs.getType(), lhs, rhs);

        if (operator == Operator.MULEQ) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.MUL);
        } else if (operator == Operator.DIVEQ) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.DIV);
        } else if (operator == Operator.MODEQ) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.MOD);
        } else if (operator == Operator.PLUSEQ) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.PLUS);
        } else if (operator == Operator.MINUSEQ) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.MINUS);
        } else if (operator == Operator.PLUSPLUS) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.PLUS);
            IntNode intOne = new IntNode();
            intOne.setValue(1);
            binaryOperNode.setRhs(intOne);
        } else if (operator == Operator.MINUSMINUS) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.MINUS);
            IntNode intOne = new IntNode();
            intOne.setValue(1);
            binaryOperNode.setRhs(intOne);
        }

        translateBinaryOperator(binaryOperNode);

        list.add(new STR(register, sp, stack.getOffsetOfVariable(lhs.getName())));
    }

    public void translateByte(ByteNode byteNode) {

        if (isDeclaring) {
            stack.add(byteNode.getName(), byteNode.getType());
        }

        list.add(new MOV(register, byteNode.getValue()));

        if (isDeclaring) {
            list.add(new STR(register, sp, stack.getOffsetOfVariable(byteNode.getName()), byteNode.getType()));
        }
    }

    public void translateShort(ShortNode shortNode) {

        if (isDeclaring) {
            stack.add(shortNode.getName(), shortNode.getType());
        }

        list.add(new LDR(register, shortNode.getValue()));

        if (isDeclaring) {
            list.add(new STR(register, sp, stack.getOffsetOfVariable(shortNode.getName()), shortNode.getType()));
        }
    }
}
