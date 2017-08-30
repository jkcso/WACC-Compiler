package arm;

public class ADD extends Instruction {

    private Register firstOperand;
    private SecondOperand secondOperand;
    private int size;
    private String flags;

    public ADD(Register destination, Register firstOperand, SecondOperand secondOperand, Enumerables.Flags flags) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.flags = flags.name();
    }

    public ADD(Register destination, Register firstOperand, int size) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.size = size;
        this.flags = "";

    }

    public ADD(Register destination, Register firstOperand, SecondOperand secondOperand) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.flags = "";
    }

    @Override
    public String toString() {
        if (secondOperand == null) {
            return String.format("\tADD%s %s, %s, #%d\n", flags, destination, firstOperand, size);
        } else {
            return String.format("\tADD%s %s, %s, %s\n", flags, destination, firstOperand, secondOperand);
        }
    }
}
