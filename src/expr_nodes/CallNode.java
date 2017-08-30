package expr_nodes;

import semantics.Function;
import translator.Translator;
import type.Type;

/**
 * This class represents parameters that are called, especially functions.
 */

public class CallNode extends ExprNode {

    private TypeNode typeNode;
    // Function object that this will point to
    private Function function;
    // List of ExprNode's which are the parameters for the function call
    private ArgumentsNode argumentsNode;

    public CallNode(Function function, ArgumentsNode argumentsNode, TypeNode typeNode) {
        this.function = function;
        this.argumentsNode = argumentsNode;
        this.typeNode = typeNode;
    }

    public Type getType() {
        return this.type;
    }

    public Function getFunction() {
        return function;
    }

    public int getArgCount() {
        return argumentsNode.getArguments().size();
    }

    public ExprNode getArg(int i) {
        return argumentsNode.getArguments().get(i);
    }

    public String toString() {
        String output = "<CALL " + function.getId() + " (";
        for (int i = 0; i < argumentsNode.getArguments().size(); i++) {
            output += argumentsNode.getArguments().get(i);
            if (i < argumentsNode.getArguments().size() - 1)
                output += ",";
        }
        output += ")>";
        return output;
    }

    @Override
    public void translate(Translator translator) {
        translator.translateCallNode(this);
    }

    public void setType(TypeNode typeNode) {
        this.typeNode = typeNode;
    }

    public TypeNode getTypeNode() {
        return typeNode;
    }

    public void setTypeType(Type type) {
        this.type = type;
    }
}
