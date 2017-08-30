package arm;

public class RSB extends Instruction {

    private Register firstOperand;
    private SecondOperand secondOperand;
    private Enumerables.Flags flags;

    public RSB(Register destination, Register firstOperand, SecondOperand secondOperand) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public RSB withFlags(Enumerables.Flags flags) {
        this.flags = flags;
        return this;
    }

    @Override
    public String toString() {

        String additionalInformation = "";

        if (flags != null) {
            additionalInformation = flags.name();
        }

        return String.format("\tRSB%s %s, %s, %s\n", additionalInformation, destination, firstOperand, secondOperand);
    }
}
