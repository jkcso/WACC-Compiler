package stat_nodes;
import node.Node;
import translator.Translator;
import type.Type;

/**
 * This class represents the declaration node that is falling in the category of statements.
 */

public class DeclarationNode extends StatNode {

    private Node node;

    public DeclarationNode(Type type, Node node) {
        this.type = type;
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateDeclaration(this);
    }
}
