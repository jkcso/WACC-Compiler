package stat_nodes;

import semantics.SymbolTable;
import translator.Translator;

/**
 * The node used to begin the statement, used to distinguish what is going to follow.
 */

public class BeginNode extends StatNode {

    private SymbolTable symbolTable;
    private StatNode statNode;

    public BeginNode(StatNode statNode) {
        this.statNode = statNode;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public StatNode getStatNode() {
        return statNode;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateBegin(this);
    }
}
