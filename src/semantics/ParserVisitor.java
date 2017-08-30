package semantics;

import antlr.WACCParser;
import antlr.WACCParserBaseVisitor;
import errors.SemanticErrorException;
import errors.SyntaxErrorException;
import expr_nodes.*;
import node.Node;
import org.antlr.v4.runtime.misc.NotNull;
import stat_nodes.*;
import type.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class is used to perform semantic analysis on the nodes of the generated AST.
 */

public class ParserVisitor extends WACCParserBaseVisitor {

    private boolean hasReturnOrExit = false;

    private SymbolTable st = new SymbolTable(null);

    private boolean visitingFunctionDeclarations = false;

    // Each function has its own symbol table and these are all stored here.
    private List<SymbolTable> functionTables = new ArrayList<>();

    // Keeps track of which function is being visited.
    private int count = 0;

    // This field has been added to aid with type checking for the integer extension.
    // It is used to push down the last known type into visitIntlit or visitSigned.
    private Type lastKnownType = null;

    private boolean visitingLoop = false;

    public SymbolTable getST() {
        return st;
    }

    @Override
    public Node visitArgList(@NotNull WACCParser.ArgListContext ctx) {
        ArgumentsNode argumentsNode = new ArgumentsNode();
        int numberOfArguments = ctx.expr().size();
        for (int i = 0; i < numberOfArguments; i++) {
            argumentsNode.add((ExprNode) this.visit(ctx.expr(i)));
        }
        return argumentsNode;
    }

    @Override
    public Node visitArrayLiter(@NotNull WACCParser.ArrayLiterContext ctx) {
        List<WACCParser.ExprContext> exprs = ctx.expr();
        List<ExprNode> exprNodeList = new ArrayList<>();

        if (exprs.size() != 0) {

            ExprNode fstElem = (ExprNode) this.visit(ctx.expr(0));

            for (WACCParser.ExprContext expr : exprs) {
                ExprNode node = (ExprNode) this.visit(expr);
                exprNodeList.add(node);
                if (!fstElem.getType().equals(node.getType())) {
                    System.err.println("The types of the elements in the array do not match,\n"
                            + "elem type: " + fstElem.getType().toString().split("[.@]")[1]
                            + "\nexpected type: " + node.getType().toString().split("[.@]")[1]);
                    System.err.println("Line: " + ctx.getStart().getLine()
                            +":" + ctx.getStart().getCharPositionInLine());
                    throw new SemanticErrorException();
                }
            }

            return new ArrayNode(exprNodeList, new ArrayType(exprNodeList.get(0).getType()));
        }
        // the empty array is of type Null.
        return new ArrayNode(exprNodeList, new NullType());
    }

    @Override
    public Node visitArrayElem(@NotNull WACCParser.ArrayElemContext ctx) {

        String name = ctx.identifier().getText();
        TypeNode result = (TypeNode) st.lookUpCurrLevelAndEnclosingLevels(name);
        Type type = ((ArrayType) result.getType()).getType();
        List<ExprNode> indices = new ArrayList<>();

        // gets the types of the elements inside the array.
        for (int i = 1; i < ctx.expr().size(); i++) {
            type = ((ArrayType) type).getType();
        }

        for (WACCParser.ExprContext expr : ctx.expr()) {
            indices.add((ExprNode) this.visit(expr));
        }

        ArrayElemNode arrayElemNode = new ArrayElemNode(type);
        arrayElemNode.setIdentifierNode(new IdentifierNode(ctx.identifier().getText(), type));
        arrayElemNode.setIndices(indices);
        return arrayElemNode;
    }

    @Override
    public Node visitUnaryOperator(@NotNull WACCParser.UnaryOperatorContext ctx) {

        ExprNode expr = (ExprNode) this.visit(ctx.expr());
        Type type = null;

        // matches the operators with the allowed types of params they can get.
        if (ctx.ORD() != null)  {
            if (expr.getType() instanceof CharType) {
                type = new IntType();
            }
        } else if (ctx.LEN() != null)   {
            if (expr.getType() instanceof StringType || expr.getType() instanceof ArrayType)   {
                type = new IntType();
            }
        } else if (ctx.MINUS() != null) {
            if (expr.getType() instanceof IntType)   {
                type = new IntType();
            }
        } else if (ctx.CHR() != null)   {
            if (expr.getType() instanceof IntType)   {
                type = new CharType();
            }
        } else if (ctx.NOT() != null)   {
            if (expr.getType() instanceof BoolType)  {
                type = new BoolType();
            }
        }

        if (type == null)   {
            System.err.println("The operator is not defined for this type");
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }   else {

            UnaryOperNode unaryOperNode = new UnaryOperNode(type, expr);

            if (ctx.NOT() != null) {
                unaryOperNode.setOperator(UnaryOperNode.Operator.NOT);
            } else if (ctx.CHR() != null) {
                unaryOperNode.setOperator(UnaryOperNode.Operator.CHR);
            } else if (ctx.MINUS() != null) {
                unaryOperNode.setOperator(UnaryOperNode.Operator.MINUS);
            } else if (ctx.ORD() != null) {
                unaryOperNode.setOperator(UnaryOperNode.Operator.ORD);
            } else if (ctx.LEN() != null) {
                unaryOperNode.setOperator(UnaryOperNode.Operator.LEN);
            }
            return unaryOperNode;
        }
    }

    @Override
    public Node visitAssignLhs(@NotNull WACCParser.AssignLhsContext ctx) {
        if (ctx.identifier() != null) {
            return (Node) this.visit(ctx.identifier());
        } else if (ctx.arrayElem() != null) {
            return (Node) this.visit(ctx.arrayElem());
        } else {
            return (Node) this.visit(ctx.pairElem());
        }
    }

    // An auxiliary function used when an integer of a lower type is cast to an integer of a higher type
    private Type highestType(IntegerType type1, IntegerType type2) {
        if (type1.precedence() > type2.precedence()) {
            return type1;
        } else {
            return type2;
        }
    }

    @Override
    public Node visitPTwo(@NotNull WACCParser.PTwoContext ctx) {

        ExprNode lhs = (ExprNode) this.visit(ctx.expr(0));
        ExprNode rhs = (ExprNode) this.visit(ctx.expr(1));

        if (!(lhs.getType().equals(rhs.getType())) && !(rhs.getType().equals(lhs.getType()))) {
            System.err.println("Arguments of operator (+,-) do not have the same type,\n"
                    + "lhs type: " + lhs.getType().toString().split("[.@]")[1]
                    + "\nrhs type: " + rhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        if (!(lhs.getType() instanceof IntegerType)) {
            System.err.println("Operator (+, -) undefined on argument types: "
                    + lhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }
        Type highestType = highestType((IntegerType) lhs.getType(), (IntegerType) rhs.getType());

        lhs.setType(highestType);
        rhs.setType(highestType);

        BinaryOperNode binaryOperNode = new BinaryOperNode(highestType, lhs, rhs);

        if (ctx.PLUS() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.PLUS);
        } else if (ctx.MINUS() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.MINUS);
        }
        return binaryOperNode;
    }

    @Override
    public Node visitBaseTypeLabel(@NotNull WACCParser.BaseTypeLabelContext ctx) {
        return (TypeNode) this.visit(ctx.baseType());
    }

    @Override
    public Node visitPFive(@NotNull WACCParser.PFiveContext ctx) {

        ExprNode lhs = (ExprNode) this.visit(ctx.expr(0));
        ExprNode rhs = (ExprNode) this.visit(ctx.expr(1));

        if (!(lhs.getType().equals(rhs.getType()))) {
            System.err.println("Arguments of operator (&) do not have the same type,\n"
                    + "lhs: " + lhs.getType().toString().split("[.@]")[1]
                    + "\nrhs: " + rhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        if (!(lhs.getType() instanceof BoolType)) {
            System.err.println("Operator (&&) undefined on argument types: "
                    + lhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        BinaryOperNode binaryOperNode = new BinaryOperNode(new BoolType(), lhs, rhs);

        binaryOperNode.setOperator(BinaryOperNode.Operator.AND);

        return binaryOperNode;
    }

    @Override
    public Node visitProgram(@NotNull WACCParser.ProgramContext ctx) {

        List<WACCParser.FuncContext> funcs = ctx.func();

        // First pass
        for (WACCParser.FuncContext func : funcs) {
            this.visit(func);
            count++;
        }

        visitingFunctionDeclarations = true;

        count = 0;
        // Second pass
        for (WACCParser.FuncContext func : funcs) {
            this.visit(func);
            count++;
        }

        hasReturnOrExit = false;

        Function main = new Function(new TypeNode(new IntType()));
        main.setId("main");
        main.setBody((StatNode) visit(ctx.stat()));
        main.setScope(st);

        st.setMain(main);

        return null;
    }

    @Override
    public Node visitBaseTypeLabelLabel(@NotNull WACCParser.BaseTypeLabelLabelContext ctx) {
        return (TypeNode) this.visit(ctx.baseType());
    }

    @Override
    public Node visitWhile(@NotNull WACCParser.WhileContext ctx) {

        visitingLoop = true;

        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        if(!exprNode.getType().equals(new BoolType())) {
            System.err.println("Expression in the while condition is incorrectly typed,\n"
                    + "expected type: " + new BoolType().toString().split("[.@]")[1]
                    + "\nactual type: " + exprNode.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        SymbolTable temporary = st;
        st = new SymbolTable(temporary);

        StatNode statNode = (StatNode) this.visit(ctx.stat());

        WhileNode whileNode = new WhileNode(exprNode, statNode);
        whileNode.setSymbolTable(st);

        st = temporary;

        visitingLoop = false;
        return whileNode;
    }

    @Override
    public Node visitCharlit(@NotNull WACCParser.CharlitContext ctx) {
        CharNode charNode = new CharNode();

        if (ctx.getText().charAt(1) == '\\') {
            charNode.setValue((int) ctx.getText().charAt(2));
        } else {
            charNode.setValue((int) ctx.getText().charAt(1));
        }

        return charNode;
    }

    @Override
    public Node visitFunctionIdentifier(@NotNull WACCParser.FunctionIdentifierContext ctx) {
        return (Node) this.visit(ctx.IDENT());
    }

    @Override
    public Node visitDoWhile(@NotNull WACCParser.DoWhileContext ctx) {
        visitingLoop = true;

        SymbolTable temporary = st;
        st = new SymbolTable(temporary);

        StatNode statNode = (StatNode) this.visit(ctx.stat());

        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        if(!exprNode.getType().equals(new BoolType())) {
            System.err.println("Expression in the do-while condition is incorrectly typed,\n"
              + "expected type: " + new BoolType().toString().split("[.@]")[1]
              + "\nactual type: " + exprNode.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
              +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        DoWhileNode doWhileNode = new DoWhileNode(exprNode, statNode);
        doWhileNode.setSymbolTable(st);

        st = temporary;

        visitingLoop = false;
        return doWhileNode;
    }

    @Override
    public Node visitStrlit(@NotNull WACCParser.StrlitContext ctx) {
        ArrayNode arrayNode = new ArrayNode(new ArrayList<>(), new ArrayType(new CharType()));
        arrayNode.setValue(ctx.STRLIT().getText());
        return arrayNode;
    }

    @Override
    public Node visitContinue(@NotNull WACCParser.ContinueContext ctx) {
        if (!visitingLoop) {
            System.err.println("Continue is used outside a loop");
            System.err.println("Line: " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }
        return new ContinueNode();

    }

    @Override
    public Node visitIf(@NotNull WACCParser.IfContext ctx) {
        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());
        // The if condition needs to be of type bool or it's not properly typed.
        if(!exprNode.getType().equals(new BoolType())) {
            System.err.println("Expression in If condition is incorrectly typed,\n"
                    + "expected type: " + new BoolType().toString().split("[.@]")[1]
                    + "\nactual type: " + exprNode.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }
        // Returns nodes of the body and type by visiting them and updates the symbol table for both bodies.
        SymbolTable temporary = st;
        st = new SymbolTable(temporary);

        StatNode ifBody = (StatNode) this.visit(ctx.stat(0));

        boolean encounteredAReturn = hasReturnOrExit;
        hasReturnOrExit = false;

        SymbolTable ifSymbolTable = st;
        st = new SymbolTable(temporary);

        StatNode elseBody = (StatNode) this.visit(ctx.stat(1));

        SymbolTable elseSymbolTable = st;
        st = temporary;

        hasReturnOrExit = (encounteredAReturn && hasReturnOrExit);

        IfNode ifNode = new IfNode(exprNode, ifBody, elseBody);
        ifNode.setIfSymbolTable(ifSymbolTable);
        ifNode.setElseSymbolTable(elseSymbolTable);

        // Sets the ifNode to the correct type. The branch that is hit is the correct type.
        if (hasReturnOrExit) {

            if (ifBody.getType().equals(elseBody.getType())) {
                ifNode.setType(ifBody.getType());
            } else if (ifBody instanceof ReturnNode && elseBody instanceof ExitNode) {
                ifNode.setType(ifBody.getType());
            } else if (ifBody instanceof ExitNode && elseBody instanceof ReturnNode) {
                ifNode.setType(elseBody.getType());
            } else if (ifBody instanceof CompositionNode && elseBody instanceof ExitNode) {
                ifNode.setType(ifBody.getType());
            } else if (ifBody instanceof ExitNode && elseBody instanceof CompositionNode) {
                ifNode.setType(elseBody.getType());
            } else if (ifBody instanceof ReturnNode && elseBody instanceof CompositionNode) {
                Node lastStatement = ((CompositionNode) elseBody).getLastStatement();
                if (lastStatement instanceof ExitNode) {
                    ifNode.setType(ifBody.getType());
                } else {
                    System.err.println("Type mismatch between return and expected type");
                    System.err.println("Line: " + ctx.getStart().getLine()
                            +":" + ctx.getStart().getCharPositionInLine());
                    throw new SemanticErrorException();
                }
            } else if (ifBody instanceof CompositionNode && elseBody instanceof ReturnNode) {
                Node lastStatement = ((CompositionNode) ifBody).getLastStatement();
                if (lastStatement instanceof ExitNode) {
                    ifNode.setType(elseBody.getType());
                } else {
                    System.err.println("Type mismatch between return and expected type");
                    System.err.println("Line: " + ctx.getStart().getLine()
                            +":" + ctx.getStart().getCharPositionInLine());
                    throw new SemanticErrorException();
                }
            } else {
                System.err.println("Type mismatch between return and expected type");
                System.err.println("Line: " + ctx.getStart().getLine()
                        +":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }
        }
        return ifNode;
    }

    @Override
    public Node visitIdentifier(@NotNull WACCParser.IdentifierContext ctx) {

        String name = ctx.IDENT().getText();

        Node node = st.lookUpCurrLevelAndEnclosingLevels(name);

        if (node instanceof TypeNode) {
            return new IdentifierNode(name, node.getType());
        } else if (node instanceof Function)  {
            System.err.println("You cannot assign a value to a function");
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        } else {
            System.err.println("Undefined identifier reference");
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }
    }

    @Override
    public Node visitArrayType(@NotNull WACCParser.ArrayTypeContext ctx) {
        return new TypeNode(new ArrayType(((TypeNode) this.visit(ctx.type())).getType()));
    }

    @Override
    public Node visitRead(@NotNull WACCParser.ReadContext ctx) {

        String name = ctx.assignLhs().getText();
        Node node = st.lookUpCurrLevelAndEnclosingLevels(name);
        Type type;

        // The idea behind the new additions is that if we are dealing with
        // an identifier then all we need to do is set the name of the read node.
        // If we deal with an expression, however, we would need to visit it and
        // then store it in the read node.

        ReadNode readNode = new ReadNode();

        if (node == null) {
            ExprNode exprNode = (ExprNode) this.visit(ctx.assignLhs());
            type = exprNode.getType();

            readNode.setExpr(exprNode);
        } else if (node instanceof Function) {
            System.err.println("Trying to input in a function or an undeclared variable");
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        } else {
            type = node.getType();
            readNode.setName(name);
        }

        if (!(type instanceof IntegerType || type instanceof CharType)) {
            System.err.println("Cannot read value of this type,\n"
                    + "expected types: " + new IntType().toString().split("[.@]")[1]
                    + " or " + new CharType().toString().split("[.@]")[1]
                    + "\nactual type: " + type.toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        readNode.setType(type);

        return readNode;
    }

    @Override
    public Node visitSigned(@NotNull WACCParser.SignedContext ctx) {

        Long value = Long.parseLong(ctx.getText());
        if (lastKnownType instanceof ByteType) {
            if (value > Byte.MAX_VALUE || value < Byte.MIN_VALUE) {
                integerSyntaxError(ctx);
            } else {
                ByteNode byteNode = new ByteNode();
                byteNode.setValue(value.byteValue());
                return byteNode;
            }
        } else if (lastKnownType instanceof ShortType) {
            if (value > Short.MAX_VALUE || value < Short.MIN_VALUE) {
                integerSyntaxError(ctx);
            } else {
                ShortNode shortNode = new ShortNode();
                shortNode.setValue(value.shortValue());
                return shortNode;
            }
        } else {
            if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
                integerSyntaxError(ctx);
            } else {
                IntNode intNode = new IntNode();
                intNode.setValue(value.intValue());
                return intNode;
            }
        }
        return null;
    }

    @Override
    public Node visitPairElemLabel(@NotNull WACCParser.PairElemLabelContext ctx) {
        return (Node) this.visit(ctx.pairElem());
    }

    @Override
    public Node visitArrayTypeLabel(@NotNull WACCParser.ArrayTypeLabelContext ctx) {
        return new TypeNode(new ArrayType(((TypeNode) this.visit(ctx.type())).getType()));
    }

    @Override
    public Node visitBrackets(@NotNull WACCParser.BracketsContext ctx) {
        return (ExprNode) this.visit(ctx.expr());
    }

    @Override
    public Node visitSecondPair(@NotNull WACCParser.SecondPairContext ctx) {

        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        if (!(exprNode.getType() instanceof PairType)) {
            System.err.println("SND is applied to an invalid type,\n"
                    + "expected type: " + new PairType(null, null).toString().split("[.@]")[1]
                    + "\nactual type: " + exprNode.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        String name = ctx.expr().getText();
        TypeNode variable = (TypeNode) st.lookUpCurrLevelAndEnclosingLevels(name);

        PairElemNode pairElemNode=  new PairElemNode(((PairType) variable.getType()).getSndType());

        pairElemNode.setValue(exprNode);

        pairElemNode.setPairName(ctx.expr().getText());

        pairElemNode.setFirst(false);

        return pairElemNode;
    }

    @Override
    public Node visitExit(@NotNull WACCParser.ExitContext ctx) {

        hasReturnOrExit = true;

        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        if (!(exprNode.getType() instanceof IntegerType)) {
            System.err.println("Exit was called with an invald type,\n"
                    + "expected type: " + new IntType().toString().split("[.@]")[1]
                    + "\nactual type: " + exprNode.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }
        return new ExitNode(exprNode);
    }

    @Override
    public Node visitArrayElemLabel(@NotNull WACCParser.ArrayElemLabelContext ctx) {
        return (ExprNode) this.visit(ctx.arrayElem());
    }

    @Override
    public Node visitBoollit(@NotNull WACCParser.BoollitContext ctx) {
        BoolNode boolNode = new BoolNode();

        if (ctx.BOOLLIT().getText().equals("true")) {
            boolNode.setValue(1);
        } else {
            boolNode.setValue(0);
        }
        return boolNode;
    }

    @Override
    public Node visitParamList(@NotNull WACCParser.ParamListContext ctx) {

        ParametersNode parametersNode = new ParametersNode();

        List<WACCParser.ParamContext> params = ctx.param();
        int size = params.size();

        // adds a new paramNode in the list and passes the list into the object returned.
        for (int i = 0; i < size; i++) {
            parametersNode.add((TypeNode) this.visit(ctx.param(i)));
        }
        return parametersNode;
    }

    @Override
    public Node visitIntlit(@NotNull WACCParser.IntlitContext ctx) {

        Long value = Long.parseLong(ctx.getText());

        if (lastKnownType instanceof ByteType) {
            if (value > Byte.MAX_VALUE || value < Byte.MIN_VALUE) {
                integerSyntaxError(ctx);
            } else {
                ByteNode byteNode = new ByteNode();
                byteNode.setValue(value.byteValue());
                return byteNode;
            }
        } else if (lastKnownType instanceof ShortType) {
            if (value > Short.MAX_VALUE || value < Short.MIN_VALUE) {
                integerSyntaxError(ctx);
            } else {
                ShortNode shortNode = new ShortNode();
                shortNode.setValue(value.shortValue());
                return shortNode;
            }
        } else if (lastKnownType instanceof IntType) {
            if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
                integerSyntaxError(ctx);
            } else {
                IntNode intNode = new IntNode();
                intNode.setValue(value.intValue());
                return intNode;
            }
        } else {
            if (value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE) {
                IntNode intNode = new IntNode();
                intNode.setValue(value.intValue());
                return intNode;
            } else {
                integerSyntaxError(ctx);
            }
        }
        return null;
    }

    // This method has been added as part of the integer extension and is an auxiliary method for the method above.
    private void integerSyntaxError(@NotNull WACCParser.IntlitContext ctx) {
        System.err.println("Invalid size assigned to a variable");
        System.err.println("Line: " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());

        throw new SyntaxErrorException();
    }

    // This method has been added as part of the integer extension and is an auxiliary method for the method above.
    private void integerSyntaxError(@NotNull WACCParser.SignedContext ctx) {
        System.err.println("Invalid size assigned to a variable");
        System.err.println("Line: " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());

        throw new SyntaxErrorException();
    }

    @Override
    public Node visitExpressionIdent(@NotNull WACCParser.ExpressionIdentContext ctx) {
        return (Node) this.visit(ctx.identifier());
    }

    // this method has been added as part of the side effect expressions extension.
    @Override
    public Node visitAssignmentEq(@NotNull WACCParser.AssignmentEqContext ctx) {
        ExprNode lhs = (IdentifierNode) this.visit(ctx.identifier());
        ExprNode rhs = null;
        String symbol = ctx.ASSIGNEQ().getSymbol().getText();

        if (symbol.equals("++") || symbol.equals("--")) {
            if (ctx.expr() != null) {
                System.err.println("Operator (++, --) doesn't need an operand ");
                System.err.println("Line: " + ctx.getStart().getLine()
                  + ":" + ctx.getStart().getCharPositionInLine());
                throw new SyntaxErrorException();
            }
        } else {
            if (ctx.expr() == null) {
                System.err.println("Operator (*=, /=, %=, +=, -=) need an operand ");
                System.err.println("Line: " + ctx.getStart().getLine()
                  + ":" + ctx.getStart().getCharPositionInLine());
                throw new SyntaxErrorException();
            }
        }

        if (ctx.expr() != null) {
            rhs = (ExprNode) this.visit(ctx.expr());
            if (!(lhs.getType() instanceof IntType) || !(rhs.getType() instanceof IntType)) {
                ExprNode notInt = lhs.getType() instanceof IntType ? rhs : lhs;
                System.err.println("Operator (*=, /=, %=, +=, -=) undefined on argument type: "
                  + notInt.getType().toString().split("[.@]")[1]);
                System.err.println("Line: " + ctx.getStart().getLine()
                  + ":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }
        } else {
            if (!(symbol.equals("++") || symbol.equals("--"))) {
                System.err.println("Undefined operator that doesn't need an operand: (" + symbol +")");
                System.err.println("Line: " + ctx.getStart().getLine()
                  + ":" + ctx.getStart().getCharPositionInLine());
                throw new SyntaxErrorException();
            }
            if (!(lhs.getType() instanceof IntType)) {
                System.err.println("Operator (++, --) undefined on argument type: "
                  + lhs.getType().toString().split("[.@]")[1]);
                System.err.println("Line: " + ctx.getStart().getLine()
                  + ":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }
        }


        AssignmentEqNode assignmentEqNode = new AssignmentEqNode(lhs, rhs);

         switch(symbol) {
             case "*=":
                 assignmentEqNode.setOperator(AssignmentEqNode.Operator.MULEQ);
                 break;
             case "/=":
                 assignmentEqNode.setOperator(AssignmentEqNode.Operator.DIVEQ);
                 break;
             case "%=":
                 assignmentEqNode.setOperator(AssignmentEqNode.Operator.MODEQ);
                 break;
             case "+=":
                 assignmentEqNode.setOperator(AssignmentEqNode.Operator.PLUSEQ);
                 break;
             case "-=":
                 assignmentEqNode.setOperator(AssignmentEqNode.Operator.MINUSEQ);
                 break;
             case "++":
                 assignmentEqNode.setOperator(AssignmentEqNode.Operator.PLUSPLUS);
                 break;
             case "--":
                 assignmentEqNode.setOperator(AssignmentEqNode.Operator.MINUSMINUS);
                 break;
         }
        return assignmentEqNode;
    }

    @Override
    public Node visitBegin(@NotNull WACCParser.BeginContext ctx) {

        SymbolTable temporary = st;

        st = new SymbolTable(temporary);

        StatNode statNode = (StatNode) this.visit(ctx.stat());
        // Need to change the scope whenever a beginNode is created.
        BeginNode beginNode = new BeginNode(statNode);

        beginNode.setSymbolTable(st);
        st = temporary;

        return beginNode;
    }

    @Override
    public Node visitArrayLiterLabel(@NotNull WACCParser.ArrayLiterLabelContext ctx) {
        return (ArrayNode) this.visit(ctx.arrayLiter());
    }

    @Override
    public Node visitPairlit(@NotNull WACCParser.PairlitContext ctx) {
        return new PairLiteralNode(new NullType());
    }

    @Override
    public Node visitFirstPair(@NotNull WACCParser.FirstPairContext ctx) {
        visitingLoop = true;

        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        if (!(exprNode.getType() instanceof PairType)) {
            System.err.println("FST is applied to an invalid type,\n"
                    + "expected type: " + new PairType(null, null).toString().split("[.@]")[1]
                    + "\nactual type: " + exprNode.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        String name = ctx.expr().getText();
        TypeNode variable = (TypeNode) st.lookUpCurrLevelAndEnclosingLevels(name);

        PairElemNode pairElemNode = new PairElemNode(((PairType) variable.getType()).getFstType());

        pairElemNode.setValue(exprNode);
        pairElemNode.setPairName(ctx.expr().getText());
        pairElemNode.setFirst(true);

        return pairElemNode;
    }

    @Override
    public Node visitFor(@NotNull WACCParser.ForContext ctx) {
        visitingLoop = true;

        StatNode declaration = (StatNode) this.visit(ctx.stat(0));

        if (!(declaration instanceof DeclarationNode)) {
            System.err.println("Declaration in the for loop condition is incorrectly typed,\n"
                    + "expected type: " + declaration.getType().toString().split("[.@]")[1]
                    + "\nactual type: " + declaration.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    + ":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        if(!exprNode.getType().equals(new BoolType())) {
            System.err.println("Expression in the for loop condition is incorrectly typed,\n"
                    + "expected type: " + new BoolType().toString().split("[.@]")[1]
                    + "\nactual type: " + exprNode.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        SymbolTable temporary = st;
        st = new SymbolTable(temporary);

        StatNode body = (StatNode) this.visit(ctx.stat(2));

        StatNode assignment = (StatNode) this.visit(ctx.stat(1));

        if (!(assignment instanceof AssignmentNode || assignment instanceof AssignmentEqNode)) {
            System.err.println("Assignment in the for loop condition is incorrectly typed,\n"
                    + "expected type: " + assignment.getType().toString().split("[.@]")[1]
                    + "\nactual type: " + assignment.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        ForNode forNode = new ForNode(declaration, exprNode, assignment, body);
        forNode.setSymbolTable(st);

        st = temporary;
        visitingLoop = false;
        return forNode;
    }

    @Override
    public Node visitSkip(@NotNull WACCParser.SkipContext ctx) {
        return new SkipNode();
    }

    @Override
    public Node visitArrayTypeLabelLabel(@NotNull WACCParser.ArrayTypeLabelLabelContext ctx) {
        return (Node) this.visit(ctx.arrayType());
    }

    @Override
    public Node visitBaseType(@NotNull WACCParser.BaseTypeContext ctx) {

        TypeNode type = null;

        if (ctx.INT() != null) {
            type = new TypeNode(new IntType());
        } else if (ctx.BOOL() != null) {
            type = new TypeNode(new BoolType());
        } else if (ctx.CHAR() != null) {
            type = new TypeNode(new CharType());
        } else if (ctx.STRING() != null) {
            type = new TypeNode(new ArrayType(new CharType()));
        } else if (ctx.BYTE() != null) {
            type = new TypeNode(new ByteType());
        } else if (ctx.SHORT() != null) {
            type = new TypeNode(new ShortType());
        }

        return type;
    }

    @Override
    public Node visitPrintln(@NotNull WACCParser.PrintlnContext ctx) {
        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        return new PrintlnNode(exprNode);
    }

    @Override
    public Node visitExprLabel(@NotNull WACCParser.ExprLabelContext ctx) {
        return (ExprNode) this.visit(ctx.expr());
    }

    @Override
    public Node visitComposition(@NotNull WACCParser.CompositionContext ctx) {

        StatNode lhs = (StatNode) this.visit(ctx.stat(0));
        CompositionNode compositionNode = new CompositionNode();
        compositionNode.add(lhs);

        if ((lhs instanceof ReturnNode) && (ctx.stat(1) != null)) {
            System.err.println("You cannot have statements after return");
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SyntaxErrorException();
        }

        if(ctx.stat(1) != null) {
            StatNode addee = (StatNode) this.visit(ctx.stat(1));

            if (hasReturnOrExit) {
                compositionNode.setType(addee.getType());
            }
            compositionNode.add(addee);
        }

        return compositionNode;
    }

    @Override
    public Node visitParam(@NotNull WACCParser.ParamContext ctx) {

        TypeNode typeNode = (TypeNode) this.visit(ctx.type());
        this.visit(ctx.defineIdentifier());

        TypeNode variable = new TypeNode(typeNode.getType());
        st.add(ctx.defineIdentifier().getText(), variable);

        return variable;
    }

    @Override
    public Node visitDefineIdentifier(@NotNull WACCParser.DefineIdentifierContext ctx) {

        if (st.lookupCurrLevelOnly(ctx.getText()) != null) {
            System.err.println("Double definition of an identifier");
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }
        return null;
    }

    @Override
    public Node visitPairTypeLabel(@NotNull WACCParser.PairTypeLabelContext ctx) {
        return (TypeNode) this.visit(ctx.pairType());
    }

    @Override
    public Node visitFree(@NotNull WACCParser.FreeContext ctx) {

        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        if (!(exprNode.getType() instanceof ArrayType || exprNode.getType() instanceof PairType)) {
            System.err.println("Trying to free an element of invalid type,\n"
                    + "expected types: " + new PairType(null, null).toString().split("[.@]")[1]
                    + " or " + new ArrayType(null).toString().split("[.@]")[1]
                    + "\nactual type: " + exprNode.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }
        return new FreeNode(exprNode);
    }

    @Override
    public Node visitBinaryOperatorIntegers(@NotNull WACCParser.BinaryOperatorIntegersContext ctx) {

        IntNode left = new IntNode();
        IntNode right = new IntNode();

        if (ctx.INTLIT() != null) {
            left.setValue(Integer.parseInt(ctx.INTLIT().getText()));
            right.setValue(Integer.parseInt(ctx.SIGNEDINTLIT(0).getText()));
        } else {
            left.setValue(Integer.parseInt(ctx.SIGNEDINTLIT(0).getText()));
            right.setValue(Integer.parseInt(ctx.SIGNEDINTLIT(1).getText()));
        }

        BinaryOperNode binaryOperNode = new BinaryOperNode(new IntType(), left, right);
        binaryOperNode.setOperator(BinaryOperNode.Operator.PLUS);

        return binaryOperNode;
    }

    @Override
    public Node visitPThree(@NotNull WACCParser.PThreeContext ctx) {

        ExprNode lhs = (ExprNode) this.visit(ctx.expr(0));
        ExprNode rhs = (ExprNode) this.visit(ctx.expr(1));

        if (!(lhs.getType().equals(rhs.getType())) && !(rhs.getType().equals(lhs.getType()))) {
            System.err.println("Arguments of operator (>, >=, <, <=) do not have the same type,\n "
                    + "lhs type: " + lhs.getType().toString().split("[.@]")[1]
                    + "\nrhs type: " + rhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        if (!(lhs.getType() instanceof IntegerType) && !(lhs.getType() instanceof CharType)) {
            System.err.println("Operator (>, >=, <, <=) undefined on argument types: "
                    + lhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        if (lhs.getType() instanceof IntegerType) {
            Type highestType = highestType((IntegerType) lhs.getType(), (IntegerType) rhs.getType());

            lhs.setType(highestType);
            rhs.setType(highestType);
        }

        BinaryOperNode binaryOperNode = new BinaryOperNode(new BoolType(), lhs, rhs);

        if (ctx.GT() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.GT);
        } else if (ctx.GTE() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.GTE);
        } else if (ctx.LT() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.LT);
        } else if (ctx.LTE() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.LTE);
        }
        return binaryOperNode;
    }

    // POne has higher precedence than PTwo and so on.
    // P<Num> are used for binary equations precedence.
    @Override
    public Node visitPOne(@NotNull WACCParser.POneContext ctx) {

        ExprNode lhs = (ExprNode) this.visit(ctx.expr(0));
        ExprNode rhs = (ExprNode) this.visit(ctx.expr(1));

        if (!(lhs.getType().equals(rhs.getType())) && !(rhs.getType().equals(lhs.getType()))) {
            System.err.println("Arguments of operator (*, /, %) do not have the same type,\n "
                    + "lhs type: " + lhs.getType().toString().split("[.@]")[1]
                    + "\nrhs type: " + rhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        if (!(lhs.getType() instanceof IntegerType)) {
            System.err.println("Operator (*, /, %) undefined on argument type: "
                    + lhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        Type highestType = highestType((IntegerType) lhs.getType(), (IntegerType) rhs.getType());

        lhs.setType(highestType);
        rhs.setType(highestType);

        BinaryOperNode binaryOperNode = new BinaryOperNode(highestType, lhs, rhs);

        if (ctx.MUL() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.MUL);
        } else if (ctx.DIV() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.DIV);
        } else if (ctx.MOD() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.MOD);
        }
        return binaryOperNode;
    }

    @Override
    public Node visitBreak(@NotNull WACCParser.BreakContext ctx) {

        if (!visitingLoop) {
            System.err.println("Break is used outside a loop");
            System.err.println("Line: " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        } else {
            return new BreakNode();
        }
    }

    @Override
    public Node visitAssignment(@NotNull WACCParser.AssignmentContext ctx) {

        ExprNode lhs = (ExprNode) this.visit(ctx.assignLhs());
        Type lhstype = lhs.getType();

        if (lhs instanceof ArrayNode) {
            lhstype = ((ArrayType) lhs.getType()).getType();
        }

        ExprNode rhs = (ExprNode) this.visit(ctx.assignRhs());
        Type rhstype = rhs.getType();

        if (!(lhstype.equals(rhstype))) {
            System.err.println("Assignment type mismatch,\n"
                    + "lhs type: " + lhstype.toString().split("[.@]")[1]
                    + "\nrhs type: " + rhstype.toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        // Previously this was always setting the name of the right hand side to the name of the left hand side
        // We do not want that to occur in the cases where x = y;
        if (!(rhs instanceof IdentifierNode)) {
            rhs.setName(lhs.getName());
        }
        return new AssignmentNode(lhs, rhs);
    }

    @Override
    public Node visitPSix(@NotNull WACCParser.PSixContext ctx) {

        ExprNode lhs = (ExprNode) this.visit(ctx.expr(0));
        ExprNode rhs = (ExprNode) this.visit(ctx.expr(1));

        if (!(lhs.getType().equals(rhs.getType()))) {
            System.err.println("Arguments of operator (||) do not have the same type,\n "
                    + "lhs type: " + lhs.getType().toString().split("[.@]")[1]
                    + "\nrhs type: " + rhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        if (!(lhs.getType() instanceof BoolType)) {
            System.err.println("Operator ( || ) undefined on argument types: "
                    + lhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        BinaryOperNode binaryOperNode = new BinaryOperNode(new BoolType(), lhs, rhs);

        binaryOperNode.setOperator(BinaryOperNode.Operator.OR);

        return binaryOperNode;
    }

    @Override
    public Node visitDeclaration(@NotNull WACCParser.DeclarationContext ctx) {

        String id = ctx.defineIdentifier().getText();

        TypeNode type = (TypeNode) this.visit(ctx.type());

        // This has been added for the integer extension
        // The idea behind it is to push the type into visitIntlit or visitSigned
        // so that type checking can be dealt with in there.
        if (type.getType() instanceof IntegerType) {
            lastKnownType = type.getType();
        }

        Node expression = (Node) this.visit(ctx.assignRhs());

        // This has been added for the integer extension
        // We need to reset the lastKnownType to be null after the right hand side has been visited.
        if (type.getType() instanceof IntegerType) {
            lastKnownType = null;
        }

        // If id is in the symbol table, it has already been defined and does not need to be declared
        if (st.lookupCurrLevelOnly(id) != null) {
            if (!(st.getDictionary().get(id) instanceof Function)) {
                System.err.println("Identifier already defined");
                System.err.println("Line: " + ctx.getStart().getLine()
                        +":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }
        }

        if (type.getType() instanceof ArrayType) {
            if (!type.getType().equals(expression.getType())) {
                System.err.println("Incorrect declaration - type mismatch,\n"
                        + "expected type: " + type.getType().toString().split("[.@]")[1]
                        + "\nactual type: " + expression.getType().toString().split("[.@]")[1]);
                System.err.println("Line: " + ctx.getStart().getLine()
                        +":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }

            // Old version of variable.
            // It was changed because we need to now the type of the array
            // and since the expression can be an empty array the type could be NullType.
            TypeNode variable = new TypeNode(expression.getType());
            st.add(id, variable);

            expression.setName(id);

            // The right hand side of an array declaration isn't always an array node.
            if (expression instanceof PairElemNode) {
                return new DeclarationNode(expression.getType(), expression);
            }


            if (ctx.type().getText().equals("string")) {
                ((ArrayNode) expression).setTruePureString();
            }



            return new stat_nodes.DeclarationNode(type.getType(), expression);

        } else if (type.getType() instanceof PairType) {

            if (expression.getType() instanceof ArrayType) {
                if (!type.getType().equals(((ArrayType) expression.getType()).getType())) {
                    System.err.println("Incorrect declaration - type mismatch,\n"
                            + "expected type: " + type.getType().toString().split("[.@]")[1]
                            + "\nactual type: " + expression.getType().toString().split("[.@]")[1]);
                    System.err.println("Line: " + ctx.getStart().getLine()
                            +":" + ctx.getStart().getCharPositionInLine());
                    throw new SemanticErrorException();
                }

                ExprNode result = ((ArrayNode) expression).getFirstElement();

                expression.setName(id);

                TypeNode variable = new TypeNode(((ArrayType) expression.getType()).getType());
                st.add(id, variable);

                return new stat_nodes.DeclarationNode(type.getType(), result);

            } else if (expression.getType() instanceof PairType || expression.getType() instanceof NullType) {

                if (!type.getType().equals(expression.getType())) {
                    System.err.println("Incorrect declaration - type mismatch,\n"
                            + "expected type: " + type.getType().toString().split("[.@]")[1]
                            + "\nactual type: " + expression.getType().toString().split("[.@]")[1]);
                    System.err.println("Line: " + ctx.getStart().getLine()
                            +":" + ctx.getStart().getCharPositionInLine());
                    throw new SemanticErrorException();
                }

                ExprNode lhs;
                ExprNode rhs;

                // Consider pair(type1, type2) name = expression;
                // Check whether type1 or type2 is a pair.
                // If neither is a pair then keep them.
                // If it is, then check whether the type of the expression is null.
                // If it is not set type1 and type2 to be the types of the expression.
                // If it is set type1 and type2 to NullType.

                if (((PairType) type.getType()).getFstType() instanceof NullType || ((PairType) type.getType()).getSndType() instanceof NullType) {
                    if (!(expression.getType() instanceof NullType)) {
                        lhs = new PairElemNode(((PairType) expression.getType()).getFstType());
                        rhs = new PairElemNode(((PairType) expression.getType()).getSndType());
                    } else {
                        lhs = new PairElemNode(new NullType());
                        rhs = new PairElemNode(new NullType());
                    }
                } else {
                    lhs = new PairElemNode(((PairType) type.getType()).getFstType());
                    rhs = new PairElemNode(((PairType) type.getType()).getSndType());
                }

                boolean isNull = false;

                // Check whether the expression is a Pair Node.
                // If it is not we know that it will be null.
                // If it is then we know that we need to copy all the information from expression into the Pair Node
                // We had problems with setting the type of lhs and rhs to be PairElemNode directly, so
                // the solution presented below bypasses that problem.

                if (expression instanceof stat_nodes.PairNode) {
                    ExprNode rhsExpr = ((stat_nodes.PairNode) expression).getRhs();
                    ExprNode lhsExpr = ((stat_nodes.PairNode) expression).getLhs();

                    ((PairElemNode) rhs).setPairName(rhsExpr.getName());
                    ((PairElemNode) lhs).setPairName(lhsExpr.getName());

                    ((PairElemNode) rhs).setValue(rhsExpr);
                    ((PairElemNode) lhs).setValue(lhsExpr);
                } else if (expression instanceof PairElemNode) {
                    TypeNode variable = new TypeNode(expression.getType());
                    st.add(id, variable);
                    expression.setName(id);
                    return new DeclarationNode(expression.getType(), expression);
                } else {
                    isNull = true;
                }

                stat_nodes.PairNode pairNode = new stat_nodes.PairNode(lhs, rhs);

                pairNode.setName(id);
                pairNode.setIsNull(isNull);

                if (expression instanceof IdentifierNode) {
                    pairNode.setReferenceName(expression.getName());
                    pairNode.setIsReferencing(true);
                }

                TypeNode variable = new TypeNode(pairNode.getType());
                st.add(id, variable);

                return new stat_nodes.DeclarationNode(type.getType(), pairNode);

            } else {
                System.err.println("Incorrect declaration - type mismatch,\n"
                        + "expected type: " + type.getType().toString().split("[.@]")[1]
                        + "\nactual type: " + expression.getType().toString().split("[.@]")[1]);
                System.err.println("Line: " + ctx.getStart().getLine()
                        +":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }
        } else {
            if (!type.getType().equals(expression.getType())) {
                System.err.println("Incorrect declaration - type mismatch,\n"
                        + "expected type: " + type.getType().toString().split("[.@]")[1]
                        + "\nactual type: " + expression.getType().toString().split("[.@]")[1]);
                System.err.println("Line: " + ctx.getStart().getLine()
                        + ":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }

            if (expression instanceof IdentifierNode) {
                expression.setType(type.getType());
                ((IdentifierNode) expression).setAssigneeName(id);
            } else {
                expression.setName(id);
            }
            if (expression instanceof UnaryOperNode) {
                ((UnaryOperNode) expression).getExpr().setName(id);
            }

            TypeNode variable = new TypeNode(expression.getType());

            st.add(id, variable);
            return new stat_nodes.DeclarationNode(type.getType(), expression);
        }

    }

    @Override
    public Node visitPair(@NotNull WACCParser.PairContext ctx) {
        return new TypeNode(new NullType());
    }

    @Override
    public Node visitPairType(@NotNull WACCParser.PairTypeContext ctx) {
        TypeNode lhs = (TypeNode) this.visit(ctx.pairElemType(0));
        TypeNode rhs = (TypeNode) this.visit(ctx.pairElemType(1));
        PairType pairType = new PairType(lhs.getType(), rhs.getType());
        return new TypeNode(pairType);
    }

    @Override
    public Node visitCall(@NotNull WACCParser.CallContext ctx) {

        this.visit(ctx.functionIdentifier());

        ArgumentsNode argumentsNode = new ArgumentsNode();

        if (ctx.argList() != null) {
            argumentsNode = (ArgumentsNode) this.visit(ctx.argList());
        }

        String name = ctx.functionIdentifier().getText();
        Function function = (Function) st.lookUpCurrLevelAndEnclosingLevels(name);

        if (function == null) {
            System.err.println("Calling an undefined function");
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        List<TypeNode> parameters = function.getParameters();
        List<ExprNode> arguments = argumentsNode.getArguments();

        if (parameters.size() != arguments.size()) {
            System.err.println("Incorrect number of arguments passed in,\n"
                    + "expected number of arguments: " + parameters.size()
                    + "\nactual number of arguments: " + arguments.size());
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        for (int i = 0; i < parameters.size(); i++) {
            if (!(parameters.get(i).getType().equals(arguments.get(i).getType()))) {
                System.err.println("Supplied argument has an invalid type,\n"
                        + "expected type: " + parameters.get(i).getType().toString().split("[.@]")[1]
                        + "\nactual type: " + arguments.get(i).getType().toString().split("[.@]")[1]);
                System.err.println("Line: " + ctx.getStart().getLine()
                        +":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }
        }

         CallNode callNode = new CallNode(function, argumentsNode, function.getReturnType());
         callNode.setTypeType(function.getReturnType().getType());
         return callNode;
    }

    @Override
    public Node visitPrint(@NotNull WACCParser.PrintContext ctx) {

        ExprNode exprNode = (ExprNode) this.visit(ctx.expr());

        return new PrintNode(exprNode);
    }

    @Override
    public Node visitNewpair(@NotNull WACCParser.NewpairContext ctx) {

        ExprNode lhs = (ExprNode) this.visit(ctx.expr(0));
        ExprNode rhs = (ExprNode) this.visit(ctx.expr(1));

        return new stat_nodes.PairNode(lhs, rhs);
    }

    @Override
    public Node visitFunc(@NotNull WACCParser.FuncContext ctx) {

        if (!ctx.functionIdentifier().getText().equals("main")) {
            hasReturnOrExit = false;

            TypeNode expectedReturnType = (TypeNode) this.visit(ctx.type());

            if (!visitingFunctionDeclarations) {

                String name = ctx.functionIdentifier().getText();

                Function function = new Function(expectedReturnType);

                if (st.lookupCurrLevelOnly(name) == null) {
                    st.add(name, function);
                } else {
                    System.err.println("Function already declared");
                    System.err.println("Line: " + ctx.getStart().getLine()
                            + ":" + ctx.getStart().getCharPositionInLine());
                    throw new SemanticErrorException();
                }

                SymbolTable temp = st;
                st = new SymbolTable(temp);

                if (ctx.paramList() != null) {
                    ParametersNode parametersNode = (ParametersNode) this.visit(ctx.paramList());
                    function.setParameters(parametersNode);
                }

                functionTables.add(st);
                st = temp;

                return null;
            }

            SymbolTable temp = st;
            st = functionTables.get(count);

            Node actualReturnType = (Node) this.visit(ctx.stat());

            if (!hasReturnOrExit) {
                System.err.println("Function body does not have a return or exit statement");
                System.err.println("Line: " + ctx.getStart().getLine()
                        + ":" + ctx.getStart().getCharPositionInLine());
                throw new SyntaxErrorException();
            }

            if (actualReturnType instanceof CompositionNode) {
                StatNode lastStatement = ((CompositionNode) actualReturnType).getLastStatement();
                if (!(lastStatement instanceof ExitNode)
                        && (!actualReturnType.getType().equals(expectedReturnType.getType()))) {
                    System.err.println("Mismatch between expected and actual return type,\n"
                            + "expected type: " + expectedReturnType.getType().toString().split("[.@]")[1]
                            + "\nactual type: " + actualReturnType.getType().toString().split("[.@]")[1]);
                    System.err.println("Line: " + ctx.getStart().getLine()
                            + ":" + ctx.getStart().getCharPositionInLine());
                    throw new SemanticErrorException();
                }

            } else if (!actualReturnType.getType().equals(expectedReturnType.getType())) {
                System.err.println("Mismatch between expected and actual return type,\n"
                        + "expected type: " + expectedReturnType.getType().toString().split("[.@]")[1]
                        + "\nactual type: " + actualReturnType.getType().toString().split("[.@]")[1]);
                System.err.println("Line: " + ctx.getStart().getLine()
                        + ":" + ctx.getStart().getCharPositionInLine());
                throw new SemanticErrorException();
            }

            Function function = (Function) st.lookUpCurrLevelAndEnclosingLevels(ctx.functionIdentifier().getText());
            function.setBody((StatNode) actualReturnType);
            function.setId(ctx.functionIdentifier().getText());
            function.setScope(st);

            st = temp;
        }
        return null;
    }

    @Override
    public Node visitPFour(@NotNull WACCParser.PFourContext ctx) {

        ExprNode lhs = (ExprNode) this.visit(ctx.expr(0));
        ExprNode rhs = (ExprNode) this.visit(ctx.expr(1));

        if (!(lhs.getType().equals(rhs.getType())) && !(rhs.getType().equals(lhs.getType()))) {
            System.err.println("Arguments of operator (==, !=) do not have the same type,\n "
                    + "lhs type: " + lhs.getType().toString().split("[.@]")[1]
                    + "\nrhs type: " + rhs.getType().toString().split("[.@]")[1]);
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }

        if (lhs.getType() instanceof IntegerType) {
            Type highestType = highestType((IntegerType) lhs.getType(), (IntegerType) rhs.getType());

            lhs.setType(highestType);
            rhs.setType(highestType);
        }

        BinaryOperNode binaryOperNode = new BinaryOperNode(new BoolType(), lhs, rhs);

        if (ctx.EQ() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.EQ);
        } else if (ctx.NEQ() != null) {
            binaryOperNode.setOperator(BinaryOperNode.Operator.NEQ);
        }
        return binaryOperNode;
    }

    @Override
    public Node visitReturn(@NotNull WACCParser.ReturnContext ctx) {
        hasReturnOrExit = true;

        ExprNode actualReturnType = (ExprNode) this.visit(ctx.expr());

        if (st.encSymTable == null) {
            System.err.println("Cannot return from the main body of the program. Only exit(code) is allowed");
            System.err.println("Line: " + ctx.getStart().getLine()
                    +":" + ctx.getStart().getCharPositionInLine());
            throw new SemanticErrorException();
        }
        return new ReturnNode(actualReturnType);
    }
}
