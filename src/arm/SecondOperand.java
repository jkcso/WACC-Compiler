package arm;

public class SecondOperand {

    public enum Shift {
        LSL, ASR,
    }

    private Number immediateOffset;
    private boolean isCharacter;
    private Shift shift;
    private Register register;
    private int value;

    public SecondOperand(Number immediateOffset, boolean isCharacter) {
        this.isCharacter = isCharacter;
        this.immediateOffset = immediateOffset;
        shift = null;
        register = null;
    }

    public SecondOperand(Register register) {
        this.register = register;
    }

    public SecondOperand(Register register, Shift shift, int value) {
        this.register = register;
        this.shift = shift;
        this.value = value;
    }

    @Override
    public String toString() {

        String operand;

        if (shift == null && register == null) {
            if (isCharacter) {
                operand = String.format("#'%s'", (char) immediateOffset.byteValue());
            } else {
                operand = String.format("#%s", immediateOffset);
            }
        } else if (shift != null) {
            operand = String.format("%s, %s #%d", register, shift, value);
        } else {
            operand = String.format("%s", register);
        }

        return operand;
    }
}
