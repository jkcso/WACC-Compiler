package stat_nodes;

import expr_nodes.ExprNode;
import translator.Translator;

/**
 * Used for an assignment which equals something at the right hand side of the '=' sign.
 */

public class AssignmentEqNode extends StatNode {

  public enum Operator {
    MULEQ, DIVEQ, MODEQ, PLUSEQ, MINUSEQ, PLUSPLUS, MINUSMINUS,
  }

  private ExprNode lhs;
  private ExprNode rhs;
  private Operator operator;

  public AssignmentEqNode(ExprNode lhs, ExprNode rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public ExprNode getLhs() {
    return lhs;
  }

  public ExprNode getRhs() {
    return rhs;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  @Override
  public void translate(Translator translator) {
    translator.translateAssignmentEq(this);
  }

}
