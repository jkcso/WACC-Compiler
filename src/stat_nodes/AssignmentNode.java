package stat_nodes;

import expr_nodes.ExprNode;
import translator.Translator;

/**
 * This class represents the assignment node that is falling in the category of statements.
 */

public class AssignmentNode extends StatNode {

    private ExprNode lhs;
    private ExprNode rhs;

    public AssignmentNode(ExprNode lhs, ExprNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public ExprNode getLhs() {
        return lhs;
    }

    public ExprNode getRhs() {
        return rhs;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateAssignment(this);
    }
}

