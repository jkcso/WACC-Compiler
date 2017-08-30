package stat_nodes;
import expr_nodes.ExprNode;
import semantics.SymbolTable;
import translator.Translator;

/**
 * This class represents the while node that is falling in the category of statements.
 */

public class WhileNode extends StatNode {

    private SymbolTable symbolTable;
    private ExprNode condition;
    private StatNode body;

    public WhileNode(ExprNode condition, StatNode body) {
        this.condition = condition;
        this.body = body;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public StatNode getBody() {
        return body;
    }

    public ExprNode getCondition() {
        return condition;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateWhile(this);
    }
}
