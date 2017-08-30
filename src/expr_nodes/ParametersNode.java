package expr_nodes;

import node.Node;
import translator.Translator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the parameters of an array or pairs that are of type expression node.
 */

public class ParametersNode extends Node {

    private List<TypeNode> list = new ArrayList<>();

    public void add(TypeNode variable) {
        list.add(variable);
    }

    public List<TypeNode> getList() {
        return list;
    }

    @Override
    public void translate(Translator translator) {
        throw new NoSuchMethodError();
    }
}
