package stat_nodes;

import expr_nodes.ExprNode;
import semantics.SymbolTable;
import translator.Translator;

/**
 * This class represents the do-while statement node.
 */

public class DoWhileNode extends StatNode {

    private ExprNode condition;
    private StatNode body;
    private SymbolTable symbolTable;

    public DoWhileNode(ExprNode exprNode, StatNode statNode) {
        this.condition = exprNode;
        this.body = statNode;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ExprNode getCondition() {
        return condition;
    }

    public void setCondition(ExprNode condition) {
        this.condition = condition;
    }

    public StatNode getBody() {
        return body;
    }

    public void setBody(StatNode statNode) {
        this.body = statNode;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateDoWhile(this);

    }
}
