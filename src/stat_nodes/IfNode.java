package stat_nodes;
import expr_nodes.ExprNode;
import semantics.SymbolTable;
import translator.Translator;

/**
 * This class represents the if node that is falling in the category of statements.
 */

public class IfNode extends StatNode {

    private ExprNode condition;
    private SymbolTable ifSymbolTable;
    private SymbolTable elseSymbolTable;

    private StatNode bodyWhenTrue;
    private StatNode bodyWhenFalse;

    public IfNode(ExprNode condition, StatNode bodyWhenTrue, StatNode bodyWhenFalse) {
        this.condition = condition;
        this.bodyWhenTrue = bodyWhenTrue;
        this.bodyWhenFalse = bodyWhenFalse;
    }

    public SymbolTable getIfSymbolTable() {
        return ifSymbolTable;
    }

    public void setIfSymbolTable(SymbolTable ifSymbolTable) {
        this.ifSymbolTable = ifSymbolTable;
    }

    public SymbolTable getElseSymbolTable() {
        return elseSymbolTable;
    }

    public void setElseSymbolTable(SymbolTable elseSymbolTable) {
        this.elseSymbolTable = elseSymbolTable;
    }

    public StatNode getBodyWhenTrue() {
        return bodyWhenTrue;
    }

    public StatNode getBodyWhenFalse() {
        return bodyWhenFalse;
    }

    public ExprNode getCondition() {
        return condition;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateIf(this);
    }
}
