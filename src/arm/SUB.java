package arm;

public class SUB extends Instruction {

    private Register firstOperand;
    private SecondOperand secondOperand;
    private int size;
    private Enumerables.Flags flags;

    public SUB(Register destination, Register firstOperand, int size) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.size = size;
    }

    public SUB(Register destination, Register firstOperand, SecondOperand secondOperand) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public SUB withFlags(Enumerables.Flags flags) {
        this.flags = flags;
        return this;
    }

    @Override
    public String toString() {

        String additionalInformation = "";

        if (flags != null) {
            additionalInformation = flags.name();
        }

        if (secondOperand != null) {
            return String.format("\tSUB%s %s, %s, %s\n", additionalInformation, destination, firstOperand, secondOperand);
        } else {
            return String.format("\tSUB%s %s, %s, #%d\n", additionalInformation, destination, firstOperand, size);
        }
    }
}
