package type;

/**
 * This class is used to represent the type of the array.  This needs to be strictly one type and predefined.
 */

public class ArrayType extends Type {

    private Type type;

    public ArrayType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof NullType) {
            return true;
        } else if (!(o instanceof ArrayType)) {
            return false;
        } else {
            return type.equals(((ArrayType) o).getType());
        }
    }
}
