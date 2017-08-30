package arm;

public class FunctionBegin extends Instruction {

    @Override
    public String toString() {
        return "\tPUSH {lr}\n";
    }
}
