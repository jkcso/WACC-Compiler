package type;

/**
 *  This class is used to represent the short type.
 */

public class ShortType extends IntegerType {
    @Override
    public boolean equals(Object o) {
        return o instanceof ShortType || o instanceof ByteType;
    }

    @Override
    public int precedence() {
        return 2;
    }
}
