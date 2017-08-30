package arm;

public class FileEnd extends Instruction {
    private PredefinedFunctions predefinedFunctions;

    public FileEnd(PredefinedFunctions predefinedFunctions) {
        this.predefinedFunctions = predefinedFunctions;
    }

    @Override
    public String toString() {
        return predefinedFunctions.toString();
    }
}
