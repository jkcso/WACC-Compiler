package expr_nodes;

import translator.Translator;
import type.Type;

/**
 * This class represents pair literal parameters that are of type expression node.
 */

public class PairLiteralNode extends ExprNode {

    public PairLiteralNode(Type type) {
        this.type = type;
    }

    @Override
    public void translate(Translator translator){
        translator.translatePairLiteral();
    }

}
