package arm;

public class POP extends Instruction {

    public POP(Register destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return String.format("\tPOP {%s}\n", destination);
    }
}
