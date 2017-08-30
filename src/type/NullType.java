package type;

/**
 * This class is used to represent the null type.  Very useful for pairs who do not have a pair type yet.
 */

public class NullType extends Type {

    @Override
    public boolean equals(Object o) {
        return ((o instanceof NullType) || (o instanceof PairType));
    }

}
