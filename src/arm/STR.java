package arm;

import type.*;

public class STR extends Instruction {

    private Register src;
    private Register dst;
    private int offset;
    private Type type;

    public STR(Register src, Register dst) {
        this.src = src;
        this.dst = dst;
    }

    public STR(Register src, Register dst, int offset) {
        this.src = src;
        this.dst = dst;
        this.offset = offset;
    }

    public STR(Register src, Register dst, int offset, Type type) {
        this.src = src;
        this.dst = dst;
        this.offset = offset;
        this.type = type;
    }

    @Override
    public String toString() {

        String additionalInformation = "";

        if (type instanceof BoolType || type instanceof CharType || type instanceof ByteType) {
            additionalInformation = "B";
        } else if (type instanceof ShortType) {
            additionalInformation = "H";
        }

        String instruction;

        if(offset == 0) {
            instruction = String.format("\tSTR%s %s, [%s]\n", additionalInformation, src, dst);
        } else {
            instruction = String.format("\tSTR%s %s, [%s, #%d]\n", additionalInformation, src, dst, offset);
        }

        return instruction;
    }
}
