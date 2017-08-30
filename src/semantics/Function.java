package semantics;

import expr_nodes.ParametersNode;
import expr_nodes.TypeNode;
import node.Node;
import stat_nodes.StatNode;
import translator.Translator;
import type.ArrayType;
import type.BoolType;
import type.CharType;
import type.IntType;

import java.util.List;

/**
 *  Function class is used to determine the return type of a function and its parameters.
 */

public class Function extends Node {

    private TypeNode returnType;
    private ParametersNode parameters = new ParametersNode();
    private String id;
    private SymbolTable st;
    private StatNode body;

    public Function(TypeNode returnType) {
        this.returnType = returnType;
    }

    public StatNode getBody() {
        return body;
    }

    public void setBody(StatNode body) {
        this.body = body;
    }

    public TypeNode getReturnType() {
        return returnType;
    }

    // set a param node to the function to make it know its params.
    public void setParameters(ParametersNode parameters) {
        this.parameters = parameters;
    }

    public List<TypeNode> getParameters() {
        return parameters.getList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // attaches a scope to the function.
    public void setScope(SymbolTable st) {
        this.st = st;
    }

    public SymbolTable getScope() {
        return st;
    }

    public int totalParameterSizeInBytes() {
        int size = 0;

        for (int i = 0; i < parameters.getList().size(); i++) {
            if (parameters.getList().get(i).getType() instanceof BoolType) {
                size += 1;
            } else if (parameters.getList().get(i).getType() instanceof CharType) {
                size += 1;
            } else if (parameters.getList().get(i).getType() instanceof IntType) {
                size += 4;
            } else if (parameters.getList().get(i).getType() instanceof ArrayType) {
                size += 4;
            }
        }
        return size;
    }

    @Override
    public void translate(Translator translator) {
        throw new NoSuchMethodError();
    }
}