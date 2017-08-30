package arm;

public class EOR extends Instruction {

    private Register firstOperand;
    private SecondOperand secondOperand;

    public EOR(Register destination, Register firstOperand, SecondOperand secondOperand) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public String toString() {
        return String.format("\tEOR %s, %s, %s\n", destination, firstOperand, secondOperand);
    }
}
