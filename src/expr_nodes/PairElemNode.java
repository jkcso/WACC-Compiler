package expr_nodes;

import translator.Translator;
import type.Type;

/**
 * This class represents the general pair elements that are of type expression node.
 */

public class PairElemNode extends ExprNode {

    private String pairName;
    private ExprNode value;
    private boolean isFirst;

    public PairElemNode(Type type) {
        this.type = type;
    }

    public String getPairName() {
        return pairName;
    }

    public void setPairName(String pairName) {
        this.pairName = pairName;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public ExprNode getValue() {
        return value;
    }

    public void setValue(ExprNode value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void translate(Translator translator) {
        translator.translatePairElem(this);
    }

}
