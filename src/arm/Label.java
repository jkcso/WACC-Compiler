package arm;


public class Label extends Instruction {

    private String label;
    private String instruction;
    private static int labelCount;
    private static int messageCount;

    public Label(String instruction) {
        this.label = instruction;
        this.instruction = String.format("%s:\n", label);
    }

    public Label(boolean isUserGenerated) {
        if (!isUserGenerated) {
            instruction = String.format("L%s:\n", labelCount);
            label = "L" + labelCount;
            labelCount++;
        } else {
            instruction = String.format("msg_%s", messageCount);
            messageCount++;
        }
    }

    public String getLabel() {
        return label;
    }

    public String getInstruction() {
        return instruction;
    }

    @Override
    public String toString() {
        return instruction;
    }
}
