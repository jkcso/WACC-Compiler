package stat_nodes;
import expr_nodes.ExprNode;
import translator.Translator;

/**
 * This class represents the print node that is falling in the category of statements.
 */

public class PrintNode extends StatNode {

    private ExprNode expr;

    public PrintNode(ExprNode expr) {
        this.expr = expr;
        this.type = expr.getType();
    }

    public ExprNode getExpr() {
        return expr;
    }

    @Override
    public void translate(Translator translator) {
        translator.translatePrint(this);
    }
}
