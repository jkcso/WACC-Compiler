package expr_nodes;

import translator.Translator;
import type.ShortType;

/**
 * This class represents a short node.
 */

public class ShortNode extends LiteralNode<Short> {

    public ShortNode() {
        this.type = new ShortType();
    }

    @Override
    public void translate(Translator translator) {
        translator.translateShort(this);
    }
}
