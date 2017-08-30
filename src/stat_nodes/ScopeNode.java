package stat_nodes;

import arm.Stack;
import translator.Translator;

/**
 * This class is used to track the statements falling in the current or previous scope.
 */

public class ScopeNode extends StatNode {

    private Stack scope;
    private StatNode stat;

    public ScopeNode(StatNode stat, Stack scope) {
        this.scope = scope;
        this.stat = stat;
    }

    public Stack getScope() {
        return scope;
    }

    public StatNode getStat() {
        return stat;
    }

    public String toString() {
        return "<SCOPE>";
    }

    @Override
    public void translate(Translator translator) {
        translator.translateScopeNode(this);
    }
}
