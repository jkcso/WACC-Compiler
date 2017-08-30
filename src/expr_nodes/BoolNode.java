package expr_nodes;
import translator.Translator;
import type.BoolType;

/**
 * This class represents the booleans that are of type expression node.
 */

public class BoolNode extends LiteralNode<Integer> {

    public BoolNode() {
        this.type = new BoolType();
    }

    @Override
    public void translate(Translator translator) {
        translator.translateBool(this);
    }
}
