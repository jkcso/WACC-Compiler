package arm;

public class AND extends Instruction {

    private final Register firstOperand;
    private final SecondOperand secondOperand;

    public AND(Register destination, Register firstOperand, SecondOperand secondOperand) {
        this.destination = destination;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    public String toString() {
        return String.format("\tAND %s, %s, %s\n", destination, firstOperand, secondOperand);
    }
}
