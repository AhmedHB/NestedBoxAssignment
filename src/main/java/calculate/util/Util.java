package calculate.util;

import calculate.NodeTreeCalculuator;
import data.input.Box;
import data.input.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Util {

    public static Stream<String> getColorStream(String coloredBox, List<Rule<Box>> rules, Rule<Box> ruleTree) {
        try {
            List<String> colors = countBoxesForTreeRule(coloredBox, ruleTree, rules);
            return colors.stream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static List<String> countBoxesForTreeRule(String coloredBox, Rule ruleTree, List<Rule<Box>> rules) throws Exception {
        List<String> uniqueColorBoxesTree = new ArrayList<>();

        NodeTreeCalculuator cbc = new NodeTreeCalculuator(rules);
        cbc.buildTreeAsc(coloredBox, ruleTree.getColorForData());
        uniqueColorBoxesTree = cbc.getUnqieueColorBoxes();

        return uniqueColorBoxesTree;
    }
}
