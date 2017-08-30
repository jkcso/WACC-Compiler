package expr_nodes;

import translator.Translator;
import type.Type;

/**
 * This class represents nodes which have a type.
 */

public class TypeNode extends ExprNode {

    public TypeNode(Type type) {
        this.type = type;
    }

    @Override
    public void translate(Translator translator) {

    }
}
