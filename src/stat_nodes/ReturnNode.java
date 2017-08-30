package stat_nodes;
import expr_nodes.ExprNode;
import translator.Translator;

/**
 * This class represents the return node that is falling in the category of statements.
 */

public class ReturnNode extends StatNode {

    public ExprNode getExpr() {
        return expr;
    }

    private ExprNode expr;

    public ReturnNode(ExprNode expr) {
        this.expr = expr;
        this.type = expr.getType();
    }

    @Override
    public void translate(Translator translator) {
        translator.translateReturn(this);
    }
}
