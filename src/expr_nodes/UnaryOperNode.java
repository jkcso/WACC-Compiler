package expr_nodes;
import translator.Translator;
import type.Type;

/**
 * This class represents the unary operators that are of type expression node.
 */

public class UnaryOperNode extends ExprNode {

    private Operator operator;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public enum Operator { LEN, ORD, CHR, NOT, MINUS }

    public ExprNode getExpr() {
        return expr;
    }

    public void setExpr(ExprNode expr) {
        this.expr = expr;
    }

    private ExprNode expr;

    public UnaryOperNode(Type type, ExprNode expr) {
        this.type = type;
        this.expr = expr;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateUnaryOpNode(this);
    }

}
