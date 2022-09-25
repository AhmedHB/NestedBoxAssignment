package calculate;

import data.input.Box;
import data.input.Rule;
import exception.ColoredBoxNotFoundException;
import exception.NoRulesException;
import rules.SearchRuleUtil;

import java.util.List;

public class RuleCalculator {
    private int nrOfParents = 0;
    private int nrOfToppParents = 0;
    public int getNrBoxesThatCanContainColoredBox(String colorBox, List<Rule<Box>> rules) throws Exception {
        nrOfParents =0;
        nrOfToppParents = 0;
        if(rules == null || rules.isEmpty()){
            throw new NoRulesException("No rules!");
        }

        List<Rule<Box>> foundRules = SearchRuleUtil.searchInList(rules, Rule::getColorsForContains, colorBox);
        if(foundRules == null || foundRules.isEmpty()){
            throw new ColoredBoxNotFoundException("Box color \"" +colorBox + "\" not found! Can't continue!");
        }

        for(Rule rule : foundRules){
            getParents(rule.getColorForData(), rules);
        }

        return nrOfToppParents;
    }

    private void getParents(String colorBox, List<Rule<Box>> rules){
        List<Rule<Box>> foundRules = SearchRuleUtil.searchInList(rules, Rule::getColorsForContains, colorBox);
        if(foundRules == null || foundRules.isEmpty()){
            nrOfToppParents++;
            return;
        }

        for(Rule rule : foundRules){
            getParents(rule.getColorForData(), rules);
            nrOfParents++;
        }
    }
}
