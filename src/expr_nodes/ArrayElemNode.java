package expr_nodes;

import translator.Translator;
import type.Type;

import java.util.List;

/**
 * This class represents array elements (specifically) that are of type expression node.
 */

public class ArrayElemNode extends ExprNode {

    private String name;

    private IdentifierNode identifierNode;

    private List<ExprNode> indices;

    public ArrayElemNode(Type type) {
        this.type = type;
    }

    public int getIndicesSize() {
        return indices.size();
    }

    public ExprNode getIndexAt(int i) {
        return indices.get(i);
    }

    public void setIndices(List<ExprNode> indices) {
        this.indices = indices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdentifierNode getIdentifierNode() {
        return identifierNode;
    }

    public void setIdentifierNode(IdentifierNode identifierNode) {
        this.identifierNode = identifierNode;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateArrayElemNode(this);
    }
}
