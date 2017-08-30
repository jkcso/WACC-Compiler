package arm;


public class FunctionEnd extends Instruction {

    @Override
    public String toString() {
        return "\t.ltorg\n";
    }
}
