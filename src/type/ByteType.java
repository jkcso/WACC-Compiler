package type;

public class ByteType extends IntegerType {
    @Override
    public boolean equals(Object o) {
        return o instanceof ByteType;
    }

    @Override
    public int precedence() {
        return 1;
    }
}
