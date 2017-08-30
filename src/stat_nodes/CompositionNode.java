package stat_nodes;
import translator.Translator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the composition node that is falling in the category of statements.
 */

public class CompositionNode extends StatNode {

    public List<StatNode> statements;

    public CompositionNode() {
        statements = new ArrayList<>();
    }

    public void add(StatNode stat) {
        statements.add(stat);
    }

    public StatNode getLastStatement() {
        return statements.get(statements.size() - 1);
    }

    @Override
    public void translate(Translator translator) {
        translator.translateComposition(this);
    }
}
