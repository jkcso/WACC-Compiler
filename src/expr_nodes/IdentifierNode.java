package expr_nodes;
import translator.Translator;
import type.Type;

/**
 * This class represents the identifiers which are of general type Expression.
 */

public class IdentifierNode extends ExprNode {

    private String assigneeName;

    public IdentifierNode(String name, Type aType) {
        this.name = name;
        this.type = aType;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateIdentifier(this);
    }
}
