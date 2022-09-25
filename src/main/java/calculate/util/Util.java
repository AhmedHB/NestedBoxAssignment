package calculate.util;

import calculate.NodeTreeCalculator;
import data.input.Box;
import data.input.Rule;

import java.util.List;
import java.util.stream.Stream;

public class Util {
    private Util(){}
    public static Stream<String> getColorStream(String coloredBox, List<Rule<Box>> rules, Rule<Box> ruleTree) {
        try {
            List<String> colors = countBoxesForTreeRule(coloredBox, ruleTree, rules);
            return colors.stream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static List<String> countBoxesForTreeRule(String coloredBox, Rule<Box> ruleTree, List<Rule<Box>> rules) throws Exception {
        NodeTreeCalculator cbc = new NodeTreeCalculator(rules);
        cbc.buildTreeAsc(coloredBox, ruleTree.getColorForData());
        return cbc.getUnqieueColorBoxes();
    }
}
