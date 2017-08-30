package arm;

import static arm.Enumerables.*;

public class BL extends Instruction {

    private String label;
    private Condition condition;
    private Flags flags;
    private Enumerables.Label branchWithLabel = Enumerables.Label.L;

    public BL(String label) {
        this.label = label;
    }

    public BL withCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    BL withFlags(Flags flags) {
        this.flags = flags;
        return this;
    }

    public BL withoutLabel() {
        this.branchWithLabel = null;
        return this;
    }

    @Override
    public String toString() {

        String additionalInformation = "";

        if (branchWithLabel != null) {
            additionalInformation += "L";
        }

        if (condition != null) {
            additionalInformation += condition.name();
        } else if (flags != null) {
            additionalInformation += flags.name();
        }

        return String.format("\tB%s %s\n", additionalInformation, label);
    }
}
