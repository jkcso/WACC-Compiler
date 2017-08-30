package expr_nodes;

import translator.Translator;
import type.ByteType;

/**
 * This class represents bytes that are of type expression node.
 */

public class ByteNode extends LiteralNode<Byte> {

    public ByteNode() {
        this.type = new ByteType();
    }

    @Override
    public void translate(Translator translator) {
        translator.translateByte(this);
    }
}
