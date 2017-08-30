package expr_nodes;

/**
 * This class represents literal nodes that are of type expression node.
 */

public abstract class LiteralNode<T extends Number> extends ExprNode {

    private T value;
    private String name;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
