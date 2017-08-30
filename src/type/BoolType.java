package type;

/**
 * This class is used to represent the boolean type having as parameters true and false.
 */

public class BoolType extends Type {

    @Override
    public boolean equals(Object o) {
        return o instanceof BoolType;
    }

}
