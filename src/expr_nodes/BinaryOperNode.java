package expr_nodes;
import translator.Translator;
import type.Type;

/**
 * This class represents the binary operators that are of type expression node.
 */

public class BinaryOperNode extends ExprNode {

    public enum Operator {
        MUL, DIV, MOD, PLUS, MINUS, GT, GTE, LT, LTE, EQ, NEQ, AND, OR
    }

    private ExprNode lhs;
    private ExprNode rhs;
    private Operator operator;

    public BinaryOperNode(Type type, ExprNode lhs, ExprNode rhs) {
        this.type = type;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void setName(String name) {
        this.name = name;
        if (lhs.getName() == null) {
            lhs.setName(name);
        }
        if (rhs.getName() == null) {
            rhs.setName(name);
        }
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public ExprNode getLhs() {
        return lhs;
    }

    public ExprNode getRhs() {
        return rhs;
    }

    public void setRhs(IntNode rhs) {
        this.rhs = rhs;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateBinaryOperator(this);
    }
}
