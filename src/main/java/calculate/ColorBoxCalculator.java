package calculate;

import calculate.util.Util;
import data.input.Box;
import data.input.Rule;
import rules.SearchRuleUtil;

import java.util.HashMap;
import java.util.List;

public class ColorBoxCalculator {
    private ColorBoxCalculator(){}
    public static int getNrUniqueColorBoxesThatCanContainInExpectedColorBox(String coloredBox, List<Rule<Box>> rules) {
        List<Rule<Box>> foundRulesContainingColor = SearchRuleUtil.searchInList(rules, Rule::getColorsForContains, coloredBox);
        HashMap<String, Integer> uniqueColors = new HashMap<>();
        foundRulesContainingColor.stream()
                .flatMap(ruleTree->Util.getColorStream(coloredBox, rules, ruleTree))
                .forEach(color ->uniqueColors.put(color, 1));
        return uniqueColors.keySet().size();
    }
    public static int getNrOfChildBoxes(String coloredBox, List<Rule<Box>> rules) throws Exception {
        NodeTreeCalculator cbc = new NodeTreeCalculator(rules);
        cbc.buildTreeDesc(coloredBox);
        return cbc.getNrOfChildBoxes();
    }
}
