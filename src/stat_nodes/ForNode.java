package stat_nodes;

import expr_nodes.ExprNode;
import semantics.SymbolTable;
import translator.Translator;

/**
 * This class represents the for node loop statement.
 */

public class ForNode extends StatNode {

    private SymbolTable symbolTable;
    private StatNode declaration;
    private ExprNode condition;
    private StatNode assignment;
    private StatNode body;

    public ForNode(StatNode declaration, ExprNode condition, StatNode assignment, StatNode body) {
        this.declaration = declaration;
        this.condition = condition;
        this.assignment = assignment;
        this.body = body;
    }

    public void setBody(StatNode body) {
        this.body = body;
    }

    public void setDeclaration(StatNode declaration) {
        this.declaration = declaration;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setCondition(ExprNode condition) {
        this.condition = condition;
    }

    public void setAssignment(StatNode assignment) {
        this.assignment = assignment;
    }

    public void setSymbolTable(SymbolTable symTable) {
        this.symbolTable = symTable;
    }

    public StatNode getDeclaration() {
        return declaration;
    }

    public StatNode getAssignment() {
        return assignment;
    }

    public StatNode getBody() {
        return body;
    }

    public ExprNode getCondition() {
        return condition;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateFor(this);
    }
}
