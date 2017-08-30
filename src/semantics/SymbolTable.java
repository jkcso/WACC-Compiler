package semantics;

import arm.Instruction;
import node.Node;
import translator.Translator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *  SymbolTable is used to keep track of the scope of Nodes(Identifiers).
 *  The SymbolTable uses a dictionary which maps a string representation of the
 *  general Node object to the actual Node.
 */

public class SymbolTable {

    SymbolTable encSymTable;
    private Function main;
    private Map<String, Node> dictionary;

    void setMain(Function main) {
        this.main = main;
    }

    SymbolTable(SymbolTable parentSt) {
        this.encSymTable = parentSt;
        this.dictionary = new LinkedHashMap<>();
    }

    // Returns the enclosing table.
    private SymbolTable getEncSymTable() {
        return this.encSymTable;
    }

    // Returns the dictionary in use.
    public Map<String, Node> getDictionary() {
        return dictionary;
    }

    // Adds an entry to the table.
    void add(String name, Node id) {
        dictionary.put(name, id);
    }

    // Looks up current scope level for given entry.
    public Node lookupCurrLevelOnly(String name) {
        if (dictionary.containsKey(name)) {
            return dictionary.get(name);
        } else {
            return null;
        }
    }

    // Looks up recursively in enclosing scopes.
    public Node lookUpCurrLevelAndEnclosingLevels(String name) {
        SymbolTable currentTable = this;

        while (currentTable != null) {
            Node id = currentTable.lookupCurrLevelOnly(name);
            if (id != null) {
                return id;
            } else {
                currentTable = currentTable.getEncSymTable();
            }
        }
        return null;
    }


    // Translates function entries of symbol table into assembly.
    public List<Instruction> translate(Translator translator) {
        for (Map.Entry<String, Node> entry : dictionary.entrySet()) {
            if (entry.getValue() instanceof Function) {
                translator.translateFunction((Function) entry.getValue());
            }
        }

        translator.translateFunction(main);

        return translator.getList();
    }
}