package expr_nodes;
import translator.Translator;
import type.CharType;

/**
 * This class represents a node of type character that are of type expression node.
 */

public class CharNode extends LiteralNode<Integer> {

    public CharNode() {
        this.type = new CharType();
    }

    @Override
    public void translate(Translator translator) {
        translator.translateChar(this);
    }
}
