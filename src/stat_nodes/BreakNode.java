package stat_nodes;

import translator.Translator;

/**
 * Created by Elina on 01/12/2016.
 */
public class BreakNode extends StatNode {
  @Override
  public void translate(Translator translator) {
    translator.translateBreak();
  }
}
