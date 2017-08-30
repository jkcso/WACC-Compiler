package type;

/**
 *  This class is used to represent the int type that is short for integer type.  The idea here is that we are going
 *  to use something similar to what Java is doing, having for example 'int a = 3' to assign the value 3 to 'a' but
 *  using 'Integer' container data type to assign the integer type to lists and other data containers.
 */

public class IntType extends IntegerType {

    @Override
    public boolean equals(Object o) {
        return o instanceof IntegerType;
    }

    @Override
    public int precedence() {
        return 3;
    }
}
