package stat_nodes;

import expr_nodes.ExprNode;
import translator.Translator;

/**
 * This class represents the read node that is falling in the category of statements.
 */

public class ReadNode extends StatNode {

    private ExprNode exprNode;

    @Override
    public void translate(Translator translator) {
        translator.translateRead(this);
    }

    public void setExpr(ExprNode exprNode) {
        this.exprNode = exprNode;
    }

    public ExprNode getExpr() {
        return exprNode;
    }
}
