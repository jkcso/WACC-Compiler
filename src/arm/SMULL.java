package arm;

public class SMULL extends Instruction {

    private Register regHigher32Bits;
    private Register regLower32Bits;
    private Register regOp1;
    private Register regOp2;

    public SMULL(Register regLower32Bits, Register regHigher32Bits, Register regOp1, Register regOp2) {
        this.regLower32Bits = regLower32Bits;
        this.regHigher32Bits = regHigher32Bits;
        this.regOp1 = regOp1;
        this.regOp2 = regOp2;
    }

    @Override
    public String toString() {
        return String.format("\tSMULL %s, %s, %s, %s\n", regLower32Bits, regHigher32Bits, regOp1, regOp2);
    }
}
