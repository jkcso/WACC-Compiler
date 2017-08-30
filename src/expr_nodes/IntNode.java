package expr_nodes;
import translator.Translator;
import type.IntType;

/**
 * This class represents the integers that are of type expression node.
 */

public class IntNode extends LiteralNode<Integer> {

    public IntNode() {
        this.type = new IntType();
    }

    @Override
    public void translate(Translator translator) {
        translator.translateInt(this);
    }
}
