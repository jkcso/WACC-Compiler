package arm;


import type.*;

public class LDR extends Instruction {

    private Register memory;
    private Number value;
    private int offset;
    private Label label;
    private Type type;
    private Enumerables.Condition condition;
    private Enumerables.Flags flags;

    public LDR(Register destination, Number value) {
        this.destination = destination;
        this.value = value;
    }

    public LDR(Register destination, Label label) {
        this.destination = destination;
        this.label = label;
    }

    public LDR(Register destination, Register memory, int offset) {
        this.destination = destination;
        this.memory = memory;
        this.offset = offset;
    }

    public LDR(Register destination, Register memory, int offset, Type type) {
        this.type = type;
        this.destination = destination;
        this.memory = memory;
        this.offset = offset;
    }

    LDR withCondition(Enumerables.Condition condition) {
        this.condition = condition;
        return this;
    }

    LDR withFlags(Enumerables.Flags flags) {
        this.flags = flags;
        return this;
    }

    @Override
    public String toString() {

        String additionalInformation;

        if (type instanceof BoolType || type instanceof CharType || type instanceof ByteType) {
            additionalInformation = "SB";
        } else if (type instanceof ShortType) {
            additionalInformation = "SH";
        } else {
            if (condition != null) {
                additionalInformation = condition.name();
            } else if (flags != null) {
                additionalInformation = flags.name();
            } else {
                additionalInformation = "";
            }
        }

        String instruction;

        if (label != null) {
            instruction = String.format("\tLDR%s %s, =%s\n", additionalInformation, destination, label.getInstruction());
        } else if (memory != null) {
            instruction = String.format("\tLDR%s %s, [%s", additionalInformation, destination, memory);
            if (offset == 0) {
                instruction += "]\n";
            } else {
                instruction += String.format(", #%d]\n", offset);
            }
        } else {
            instruction = String.format("\tLDR%s %s, =%s\n", additionalInformation, destination, value);
        }

        return instruction;
    }
}
