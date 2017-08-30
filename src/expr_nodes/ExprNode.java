package expr_nodes;

import node.Node;
import node.Translatable;
import type.*;

/**
 * This class represents the nodes of expressions that are falling below the general type of Node.
 */

public abstract class ExprNode extends Node implements Translatable{

    public ExprNode() {}

    public ExprNode(Type type) {
        this.type = type;
    }

    public int getSize() {
        int size = 0;

        if ((type instanceof PairType)
                || (type instanceof ArrayType)) {
            size += 4;
        } else if (type instanceof BoolType) {
            size += 1;
        } else if (type instanceof CharType) {
            size += 1;
        } else if (type instanceof IntType) {
            size += 4;
        } else if (type instanceof NullType) {
            size += 4;
        }
        return size;
    }
}
