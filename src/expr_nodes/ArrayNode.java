package expr_nodes;

import node.Node;
import translator.Translator;
import type.ArrayType;
import type.CharType;
import type.PairType;
import type.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents arrays that are of type expression node.
 */

public class ArrayNode extends ExprNode {

    private List<ExprNode> list = new ArrayList<>();
    private String value;
    private boolean pureString;
    private String name;

    public ArrayNode(List<ExprNode> exprNodeList, Type type) {
        this.pureString = false;
        this.list = exprNodeList;
        this.type = type;
    }

    public ExprNode getFirstElement() {
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public ExprNode getElementAt(int i) {
        return list.get(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPureString() {
        return this.pureString;
    }

    public void setTruePureString() {
        this.pureString = true;
    }

    public int getArrayLength() {
        return list.size();
    }

    public int getSizeInBytes() {

        if (list.isEmpty()) {
            return 4;
        }

        if (pureString) {
            return 4;
        }

        int size;

        Node node = this.getFirstElement();

        if ((node.getType() instanceof PairType) || (node.getType() instanceof ArrayType) || node instanceof IntNode) {
            size = 4;
        } else {
            size = 1;
        }

        size *= list.size();

        size += 4;

        return size;
    }

    @Override
    public void translate(Translator translator) {
        if (type.equals(new ArrayType(new CharType()))) {
            translator.translateString(this);
        } else {
            translator.translateArrayLiteralNode(this);
        }
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
