package arm;

public class PUSH extends Instruction {

    public PUSH(Register register) {
        this.destination = register;
    }

    public String toString() {
        return String.format("\tPUSH {%s}\n", destination);
    }
}
