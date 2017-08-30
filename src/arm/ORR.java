package arm;

public class ORR extends Instruction {

    private Register firstOperand;
    private SecondOperand secondOperand;

    public ORR(Register destination, Register firstOperand, SecondOperand secondOperand) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public String toString() {
        return String.format("\tORR %s, %s, %s\n", destination, firstOperand, secondOperand);
    }
}
