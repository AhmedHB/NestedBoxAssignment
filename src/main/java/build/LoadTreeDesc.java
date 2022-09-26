package build;

import data.input.Box;
import data.input.Rule;
import data.tree.TreeNode;
import exception.ColoredBoxNotFoundException;
import exception.NoRulesException;
import rules.SearchRuleUtil;
import java.util.List;

public class LoadTreeDesc extends LoadTree{
    public static TreeNode<Box> buildTreeWithUsingColorBoxAmount(String rootBoxColor, List<Rule<Box>> rules) throws Exception {
        countBoxes = 0;
        countLeaves = 0;

        if(rules == null || rules.isEmpty()){
            throw new NoRulesException("No rules!");
        }

        List<Rule<Box>> foundRootRules = SearchRuleUtil.search(rules, Rule::getColorForData, rootBoxColor);
        if(foundRootRules == null || foundRootRules.isEmpty()){
            throw new ColoredBoxNotFoundException("Box color \"" +rootBoxColor + "\" not found! Can't continue!");
        }

        TreeNode<Box> rootTreeNode = createRootTreeNode(rootBoxColor);

        Rule<Box> rootRule = foundRootRules.get(0);
        List<Box> rootRuleChildren = rootRule.getContains();

        rootRuleChildren.forEach(childRule ->{
            Box rootChildBox = createBox(childRule.getColor());
            List<Rule<Box>> foundChildRules = SearchRuleUtil.search(rules, Rule::getColorForData, childRule.getColor());
            for(int i=0;i<childRule.getAmount();i++) {
                TreeNode<Box> rootChildBoxTreeNode = rootTreeNode.addChild(rootChildBox);
                if(foundChildRules !=null && !foundChildRules.isEmpty()) {
                    createChildTreeNode(rootChildBoxTreeNode, foundChildRules, rules);
                    countBoxes++;
                }
            }
        });

        rootTreeNode.setContains(countBoxes);
        rootTreeNode.setNrOfLeaves(countLeaves);

        return rootTreeNode;
    }
    private static void createChildTreeNode( TreeNode<Box> treeNode,
                                             List<Rule<Box>> childRules,
                                             List<Rule<Box>> rules){
        if(childRules == null || childRules.isEmpty()){
            return;
        }

        Rule<Box> childRule = childRules.get(0);
        List<Box> ruleChildren = childRule.getContains();

        ruleChildren.forEach(ruleChild ->{
            Box childBox = createBox(ruleChild.getColor());
            List<Rule<Box>> foundChildRules = SearchRuleUtil.search(rules, Rule::getColorForData, ruleChild.getColor());
            for(int i=0;i<ruleChild.getAmount();i++) {
                TreeNode<Box> childBoxTreeNode = treeNode.addChild(childBox);
                if(foundChildRules !=null && !foundChildRules.isEmpty()) {
                    createChildTreeNode(childBoxTreeNode, foundChildRules, rules);
                    countBoxes++;
                }else{
                    countLeaves++;
                }
            }
        });
    }
}
