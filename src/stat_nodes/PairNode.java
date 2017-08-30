package stat_nodes;
import expr_nodes.ExprNode;
import translator.Translator;
import type.PairType;

/**
 * This class represents the pair node that is falling in the category of statements.
 */

public class PairNode extends StatNode {

    private ExprNode lhs;
    private ExprNode rhs;
    private String name;
    private String referenceName;
    private boolean isReferencing;
    private boolean isNull;

    public PairNode(ExprNode lhs, ExprNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.type = new PairType(lhs.getType(), rhs.getType());
    }


    public String getReferenceName() {
        return referenceName;
    }


    public void setReferenceName(String referenceName)  {
        this.referenceName = referenceName;
    }

    public ExprNode getLhs() {
        return lhs;
    }

    public ExprNode getRhs() {
        return rhs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setIsNull(boolean isNull) {
        this.isNull = isNull;
    }

    public boolean isReferencing() {
        return isReferencing;
    }

    public void setIsReferencing(boolean isReferencing) {
        this.isReferencing = isReferencing;
    }

    @Override
    public void translate(Translator translator) {
        translator.translatePair(this);
    }
}
