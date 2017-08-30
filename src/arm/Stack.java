package arm;

import expr_nodes.TypeNode;
import node.Node;
import semantics.Function;
import semantics.SymbolTable;
import type.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class Stack {

    private Map<String, Integer> map = new LinkedHashMap<>();

    private SymbolTable symbolTable;
    private int sizeInBytes = 0;

    private Stack parentStack;
    private int currentSizeInBytes = 0;

    public Stack(SymbolTable symbolTable) {

        this.symbolTable = symbolTable;

        for(Map.Entry<String, Node> entry : symbolTable.getDictionary().entrySet()) {

            if (!(entry.getValue() instanceof Function)) {
                if(entry.getValue().getType() instanceof BoolType
                        || entry.getValue().getType() instanceof CharType
                        || entry.getValue().getType() instanceof ByteType) {
                    sizeInBytes++;
                } else if (entry.getValue().getType() instanceof ShortType) {
                    sizeInBytes += 2;
                } else {
                    sizeInBytes += 4;
                }
            }
        }
    }

    public void add(String name, Type type) {

        if (type instanceof BoolType || type instanceof CharType) {
            currentSizeInBytes++;
        } else if (type instanceof  ShortType) {
            currentSizeInBytes += 2;
        } else {
            currentSizeInBytes += 4;
        }

        map.put(name, currentSizeInBytes);
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public void setParentStack(Stack parentStack) {
        this.parentStack = parentStack;
    }

    public int getOffsetOfVariable(String name) {

        Integer sizeOfStackAtVariableInBytes = map.get(name);

        if (sizeOfStackAtVariableInBytes == null) {
            return parentStack.getOffsetOfVariable(name) + sizeInBytes;
        } else {
            return sizeInBytes - sizeOfStackAtVariableInBytes;
        }
    }

    public Type getTypeOfVariable(String name) {
        TypeNode typeNode = (TypeNode) symbolTable.lookUpCurrLevelAndEnclosingLevels(name);
        return typeNode.getType();
    }
}
