package stat_nodes;

import expr_nodes.ExprNode;
import translator.Translator;

/**
 * This class represents the free node that is falling in the category of statements.
 */

public class FreeNode extends StatNode {

    private ExprNode expr;

    public FreeNode(ExprNode expr) {
        this.expr = expr;
        this.type = expr.getType();
    }

    public ExprNode getExpr() {
        return expr;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateFree(this);
    }
}
