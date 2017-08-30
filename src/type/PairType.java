package type;

/**
 *  This class is used to represent the pair type.  Then when you know that something is a pair, then you can choose
 *  between a char, int etc type of pair.  So the hierarchy is that you have pair type first and then something more
 *  specific.
 */

public class PairType extends Type {

    private Type fstType;
    private Type sndType;

    public PairType(Type fstType, Type sndType) {
        this.fstType = fstType;
        this.sndType = sndType;
    }
    public Type getFstType() {
        return fstType;
    }

    public Type getSndType() {
        return sndType;
    }

    @Override
    public boolean equals(Object o) {
        return ((o instanceof PairType) || (o instanceof NullType));
    }

}
