package node;
import translator.Translator;
import type.Type;

/**
 * This class represents a node and it is in the highest level in the hierarchy of our design.
 */

public abstract class Node implements Translatable {

    protected String name;
    protected Type type;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public abstract void translate(Translator translator);
}
