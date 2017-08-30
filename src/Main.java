import antlr.WACCLexer;
import antlr.WACCParser;
import arm.Instruction;
import errors.SemanticErrorException;
import errors.SyntaxErrorException;
import errors.SyntaxErrorListener;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import semantics.ParserVisitor;
import semantics.SymbolTable;
import translator.Translator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This is the Main class of our implementation where the program
 * takes a sequence of instruction to execute.
 */

public class Main {

    private static final int EXIT_SYNTAX_ERROR = 100;
    private static final int EXIT_SEMANTIC_ERROR = 200;

    public static void main(String[] args) throws Exception {

        ANTLRInputStream input = new ANTLRFileStream(args[0]);
        WACCLexer lexer = new WACCLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        WACCParser parser = new WACCParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorListener());

        ParseTree tree = null;

        try {
            tree = parser.program();
        } catch (SyntaxErrorException e) {
            System.exit(EXIT_SYNTAX_ERROR);
        }

        ParserVisitor visitor = new ParserVisitor();

        try {
            visitor.visit(tree);
        } catch (SemanticErrorException e) {
            System.exit(EXIT_SEMANTIC_ERROR);
        } catch (SyntaxErrorException e) {
            System.exit(EXIT_SYNTAX_ERROR);
        }

        Translator translator = new Translator();
        SymbolTable st = visitor.getST();
        List<Instruction> list = st.translate(translator);

        PrintWriter pw = null;
        String filename = args[1];
        String path = args[2];
        File file = new File(path + "/" + filename + ".s");

        try {
            FileWriter fw = new FileWriter(file, false);
            pw = new PrintWriter(fw);
            for (Instruction node : list) {
                pw.print(node.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

        Optimiser.optimise(file);
    }
}
