package stat_nodes;

import expr_nodes.ExprNode;
import translator.Translator;

/**
 * This class represents the exit node that is falling in the category of statements.
 */

public class ExitNode extends StatNode {

    private ExprNode expr;

    public ExitNode(ExprNode expr) {
        this.expr = expr;
        this.type = expr.getType();
    }

    public ExprNode getExpr() {
        return expr;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateExit(this);
    }
}
