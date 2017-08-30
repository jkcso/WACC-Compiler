package type;

/**
 *  This class is used to represent the String type.
 */

public class StringType extends Type {

    @Override
    public boolean equals(Object o) {
        return o instanceof StringType;
    }

}
