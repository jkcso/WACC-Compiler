package expr_nodes;

import node.Node;
import translator.Translator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents arguments that are of type expression node.
 */

public class ArgumentsNode extends Node {

    private List<ExprNode> arguments = new ArrayList<>();

    public void add(ExprNode exprNode) {
        arguments.add(exprNode);
    }

    public List<ExprNode> getArguments() {
        return arguments;
    }

    @Override
    public void translate(Translator translator) {
        throw new NoSuchMethodError();
    }
}
