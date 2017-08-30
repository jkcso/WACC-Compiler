package arm;

public class CMP extends Instruction {

    Register register;
    private int value;
    private SecondOperand operand;

    public CMP(Register register, int value) {
        this.register = register;
        this.value = value;
    }

    public CMP(Register register, SecondOperand operand) {
        this.register = register;
        this.operand = operand;
    }

    @Override
    public String toString() {
        if (operand != null) {
            return String.format("\tCMP %s, %s\n", register, operand);
        } else {
            return String.format("\tCMP %s, #%s\n", register, value);
        }
    }
}
