import java.io.*;

/**
 *  Optimises the firstly generated assembly code.  Used as intermediary point between code analysis and code
 *  generation.
 */

class Optimiser {

    // Creates a new file which is then overwritten with the optimised assembly code

    static void optimise(File file) throws IOException {

        File temp = new File(file.getAbsolutePath() + ".tmp");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        String prevLine = null;
        FileWriter fw = new FileWriter(temp, false);
        PrintWriter pw = new PrintWriter(fw);


        while ((line = bufferedReader.readLine()) != null) {

            String[] components = line.split(" ", 2);

            /*
             * If the previous line is a STR and current line is an LDR with the same instructions afterwards,
             * eg. STR r4, [SP]
             *     LDR r4, [SP]
             *
             * the redundant LDR instruction is not written 
             */
            if (components[0].equals("\tLDR") && prevLine != null) {
                String[] prevLineComponents = prevLine.split(" ", 2);
                if (prevLineComponents.length > 1 && components[1].equals(prevLineComponents[1])
                        && prevLineComponents[0].equals("\tSTR") ) {
                    line = bufferedReader.readLine();
                }
            }

            prevLine = line;

            pw.print(line + "\n");
            pw.flush();

        }


        pw.close();
        bufferedReader.close();

        if (!file.delete()) {
            System.err.println("File hasn't been deleted");
        }

        if(!temp.renameTo(file)) {
            System.err.println("File hasn't been renamed");
        }
    }
}
