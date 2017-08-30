package stat_nodes;

import translator.Translator;

/**
 * Used for the continue statement to make the code jump into another line when the loop needs to exit.
 */

public class ContinueNode extends StatNode{

  @Override
  public void translate(Translator translator) {
    translator.translateContinue();
  }
}
