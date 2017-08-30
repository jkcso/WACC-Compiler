package arm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static arm.Enumerables.Condition.*;
import static arm.Enumerables.Flags.*;

public class PredefinedFunctions {

    private List<Instruction> list;
    private FileBegin fileBegin;
    private Map<PredefinedFunction, List<Instruction>> predefinedFunctions;

    public enum PredefinedFunction {
        P_PRINT_STRING, P_PRINT_INT, P_PRINT_BOOL, P_PRINTLN, P_THROW_RUNTIME_ERROR,
        P_CHECK_DIVIDE_BY_ZERO, P_THROW_OVERFLOW_ERROR, P_THROW_OVERFLOW_ERROR_NE,
        P_CHECK_ARRAY_BOUNDS, P_PRINT_REFERENCE, P_READ_INT, P_READ_CHAR,
        P_CHECK_NULL_POINTER, P_FREE_PAIR
    }

    private Register r0 = new Register(0);
    private Register r1 = new Register(1);
    private Register r2 = new Register(2);
    private Register sp = new Register(13);
    private Register pc = new Register(15);

    public PredefinedFunctions(List<Instruction> list, FileBegin fileBegin) {
        this.list = list;
        this.fileBegin = fileBegin;
        predefinedFunctions = new HashMap<>();
    }

    public void add(PredefinedFunction predefinedFunction) {
        switch (predefinedFunction) {
            case P_PRINT_BOOL:
                list.add(new BL("p_print_bool"));
                printBool();
                break;
            case P_PRINT_INT:
                list.add(new BL("p_print_int"));
                printInt();
                break;
            case P_PRINT_STRING:
                list.add(new BL("p_print_string"));
                printString();
                break;
            case P_PRINTLN:
                list.add(new BL("p_print_ln"));
                println();
                break;
            case P_PRINT_REFERENCE:
                list.add(new BL("p_print_reference"));
                printReference();
                break;
            case P_THROW_OVERFLOW_ERROR:
                list.add(new BL("p_throw_overflow_error").withFlags(VS));
                printOverflowError();
                break;
            case P_THROW_OVERFLOW_ERROR_NE:
                list.add(new BL("p_throw_overflow_error").withCondition(NE));
                printOverflowError();
                break;
            case P_THROW_RUNTIME_ERROR:
                list.add(new BL("p_throw_runtime_error"));
                printRuntimeError();
                break;
            case P_CHECK_DIVIDE_BY_ZERO:
                list.add(new BL("p_check_divide_by_zero"));
                checkDivideByZero();
                break;
            case P_CHECK_ARRAY_BOUNDS:
                list.add(new BL("p_check_array_bounds"));
                checkArrayBounds();
                break;
            case P_CHECK_NULL_POINTER:
                list.add(new BL("p_check_null_pointer"));
                checkNullPointer();
                break;
            case P_READ_INT:
                list.add(new BL("p_read_int"));
                printReadInt();
                break;
            case P_READ_CHAR:
                list.add(new BL("p_read_char"));
                printReadChar();
                break;
            case P_FREE_PAIR:
                list.add(new BL("p_free_pair"));
                freePair();
        }
    }

    private void freePair() {
        List<Instruction> function = new LinkedList<>();

        Message message = new Message("NullReferenceError: dereference a null reference\\n\\0", false);

        function.add(new Label("p_free_pair"));

        fileBegin.addMessage(message);

        function.add(new FunctionBegin());

        function.add(new CMP(r0, new SecondOperand(0, false)));

        function.add(new LDR(r0, fileBegin.getLabel(message)).withCondition(EQ));

        function.add(new BL("p_print_runtime_error").withoutLabel().withCondition(EQ));

        printRuntimeError();

        function.add(new PUSH(r0));

        function.add(new LDR(r0, r0, 0));

        function.add(new BL("free"));

        function.add(new LDR(r0, sp, 0));

        function.add(new LDR(r0, r0, 4));

        function.add(new BL("free"));

        function.add(new POP(r0));

        function.add(new BL("free"));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_FREE_PAIR, function);
    }

    private void printReadInt() {
        Message message = new Message("%d\\0", false);

        fileBegin.addMessage(message);

        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_read_int"));

        function.add(new FunctionBegin());

        function.add(new MOV(r1, new SecondOperand(r0)));

        function.add(new LDR(r0, fileBegin.getLabel(message)));

        function.add(new ADD(r0, r0, new SecondOperand(4, false)));

        function.add(new BL("scanf"));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_READ_INT, function);
    }

    private void printReadChar() {
        Message message = new Message("%c\\0", false);

        fileBegin.addMessage(message);

        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_read_char"));

        function.add(new FunctionBegin());

        function.add(new MOV(r1, new SecondOperand(r0)));

        function.add(new LDR(r0, fileBegin.getLabel(message)));

        function.add(new ADD(r0, r0, new SecondOperand(4, false)));

        function.add(new BL("scanf"));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_READ_CHAR, function);
    }

    private void checkNullPointer() {
        Message message = new Message("NullReferenceError: dereference a null reference\\n\\0", false);

        fileBegin.addMessage(message);

        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_check_null_pointer"));

        function.add(new FunctionBegin());

        function.add(new CMP(r0, new SecondOperand(0, false)));

        function.add(new LDR(r0, fileBegin.getLabel(message)).withCondition(EQ));

        function.add(new BL("p_print_runtime_error").withCondition(EQ));

        printRuntimeError();

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_CHECK_NULL_POINTER, function);
    }

    private void printReference() {
        Message message = new Message("%p\\0", false);

        fileBegin.addMessage(message);

        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_print_reference"));

        function.add(new FunctionBegin());

        function.add(new MOV(r1, new SecondOperand(r0)));

        function.add(new LDR(r0, fileBegin.getLabel(message)));

        function.add(new ADD(r0, r0, new SecondOperand(4, false)));

        function.add(new BL("printf"));

        function.add(new MOV(r0, new SecondOperand(0, false)));

        function.add(new BL("fflush"));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_PRINT_REFERENCE, function);
    }

    private void printString() {
        Message message = new Message("%.*s\\0", false);

        fileBegin.addMessage(message);

        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_print_string"));

        function.add(new FunctionBegin());

        function.add(new LDR(r1, r0, 0));

        function.add(new ADD(r2, r0, new SecondOperand(4, false)));

        function.add(new LDR(r0, fileBegin.getLabel(message)));

        function.add(new ADD(r0, r0, new SecondOperand(4, false)));

        function.add(new BL("printf"));

        function.add(new MOV(r0, new SecondOperand(0, false)));

        function.add(new BL("fflush"));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_PRINT_STRING, function);
    }

    private void printBool() {
        Message trueMessage = new Message("true\\0", false);

        Message falseMessage = new Message("false\\0", false);

        fileBegin.addMessage(trueMessage);

        fileBegin.addMessage(falseMessage);

        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_print_bool"));

        function.add(new FunctionBegin());

        function.add(new CMP(r0, new SecondOperand(0, false)));

        function.add(new LDR(r0, fileBegin.getLabel(trueMessage)).withCondition(NE));

        function.add(new LDR(r0, fileBegin.getLabel(falseMessage)).withCondition(EQ));

        function.add(new ADD(r0, r0, new SecondOperand(4, false)));

        function.add(new BL("printf"));

        function.add(new MOV(r0, new SecondOperand(0, false)));

        function.add(new BL("fflush"));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_PRINT_BOOL, function);
    }

    private void printInt() {
        Message message = new Message("%d\\0", false);

        fileBegin.addMessage(message);

        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_print_int"));

        function.add(new FunctionBegin());

        function.add(new MOV(r1, new SecondOperand(r0)));

        function.add(new LDR(r0, fileBegin.getLabel(message)));

        function.add(new ADD(r0, r0, new SecondOperand(4, false)));

        function.add(new BL("printf"));

        function.add(new MOV(r0, new SecondOperand(0, false)));

        function.add(new BL("fflush"));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_PRINT_INT, function);
    }

    private void println() {
        Message message = new Message("\\0", false);

        fileBegin.addMessage(message);

        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_print_ln"));

        function.add(new FunctionBegin());

        function.add(new LDR(r0, fileBegin.getLabel(message)));

        function.add(new ADD(r0, r0, new SecondOperand(4, false)));

        function.add(new BL("puts"));

        function.add(new MOV(r0, new SecondOperand(0, false)));

        function.add(new BL("fflush"));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_PRINTLN, function);
    }

    private void printRuntimeError() {
        List<Instruction> function = new LinkedList<>();

        function.add(new Label("p_print_runtime_error"));

        function.add(new BL("p_print_string"));

        printString();

        function.add(new MOV(r0, new SecondOperand(-1, false)));

        function.add(new BL("exit"));

        predefinedFunctions.put(PredefinedFunction.P_THROW_RUNTIME_ERROR, function);
    }

    private void checkDivideByZero() {
        List<Instruction> function = new LinkedList<>();

        Message message = new Message("DivideByZeroError: divide or modulo by zero\\n\\0", false);

        fileBegin.addMessage(message);

        function.add(new Label("p_check_divide_by_zero"));

        function.add(new FunctionBegin());

        function.add(new CMP(r1, new SecondOperand(0, false)));

        function.add(new LDR(r0, fileBegin.getLabel(message)).withCondition(EQ));

        function.add(new BL("p_print_runtime_error").withCondition(EQ));

        printRuntimeError();

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_CHECK_DIVIDE_BY_ZERO, function);
    }

    private void printOverflowError() {
        List<Instruction> function = new LinkedList<>();

        Message message = new Message("OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n", false);

        fileBegin.addMessage(message);

        function.add(new Label("p_throw_overflow_error"));

        function.add(new LDR(r0, fileBegin.getLabel(message)));

        function.add(new BL("p_print_runtime_error"));

        printRuntimeError();

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_THROW_OVERFLOW_ERROR, function);
    }

    private void checkArrayBounds() {
        List<Instruction> function = new LinkedList<>();

        Message negativeIndexMessage = new Message("ArrayIndexOutOfBoundsError: negative index\\n\\0", false);

        Message tooLargeIndexMessage = new Message("ArrayIndexOutOfBoundsError: index too large\\n\\0", false);

        fileBegin.addMessage(negativeIndexMessage);

        fileBegin.addMessage(tooLargeIndexMessage);

        function.add(new Label("p_check_array_bounds"));

        function.add(new FunctionBegin());

        function.add(new CMP(r0, new SecondOperand(0, false)));

        function.add(new LDR(r0, fileBegin.getLabel(negativeIndexMessage)).withCondition(LT));

        function.add(new BL("p_print_runtime_error").withCondition(LT));

        printRuntimeError();

        function.add(new LDR(r1, r1, 0));

        function.add(new CMP(r0, new SecondOperand(r1)));

        function.add(new LDR(r0, fileBegin.getLabel(tooLargeIndexMessage)).withFlags(CS));

        function.add(new BL("p_print_runtime_error").withFlags(CS));

        function.add(new POP(pc));

        predefinedFunctions.put(PredefinedFunction.P_CHECK_ARRAY_BOUNDS, function);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (PredefinedFunction key : predefinedFunctions.keySet()) {
            List<Instruction> function = predefinedFunctions.get(key);
            for (Instruction node : function) {
                sb.append(node.toString());
            }
        }

        return sb.toString();
    }
}
