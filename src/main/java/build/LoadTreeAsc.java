package build;

import data.input.Box;
import data.input.Rule;
import data.tree.TreeNode;
import exception.NoRulesException;
import rules.SearchRuleUtil;

import java.util.ArrayList;
import java.util.List;

public class LoadTreeAsc extends LoadTree{
    public static TreeNode<Box> buildTreeWithoutUsingColorBoxAmount(String rootBoxColor, String colorBox, List<Rule<Box>> rules) throws Exception {
        countBoxes = 0;
        if(rules == null || rules.isEmpty()){
            throw new NoRulesException("No rules!");
        }

        TreeNode<Box> rootTreeNode = createRootTreeNode(rootBoxColor);
        Box parentBox1 = createBox(colorBox);
        TreeNode<Box> rootChildBoxTreeNode = rootTreeNode.addChild(parentBox1);
        countBoxes++;

        List<Rule<Box>> foundParentRules = new ArrayList<>(SearchRuleUtil.searchInList(rules, Rule::getColorsForContains, colorBox));
        if(foundParentRules.isEmpty()){
            return rootTreeNode;
        }

        foundParentRules.forEach(parentRule ->{
            Box parentBox2 = createBox(parentRule.getColorForData());
            TreeNode<Box> rootChildBoxTreeNode2 = rootChildBoxTreeNode.addChild(parentBox2);
            List<Rule<Box>> foundParentRules2 = SearchRuleUtil.searchInList(rules, Rule::getColorsForContains, parentRule.getColorForData());
            if((foundParentRules2 !=null && !foundParentRules2.isEmpty())
                    && !parentRule.getColorForData().equalsIgnoreCase(rootBoxColor)) {
                createChildTreeNode(rootChildBoxTreeNode2, foundParentRules2, rules, rootBoxColor);
            }
        });

        rootTreeNode.setContains(countBoxes);

        return rootTreeNode;
    }

    private static void createChildTreeNode( TreeNode<Box> treeNode,
                                             List<Rule<Box>> parentRules,
                                             List<Rule<Box>> rules,
                                             String endColor){
        if(parentRules == null || parentRules.isEmpty()){
            return;
        }

        parentRules.forEach(parentRule->{
            Box parentBox = createBox(parentRule.getColorForData());
            TreeNode<Box> rootChildBoxTreeNode = treeNode.addChild(parentBox);
            List<Rule<Box>> foundParentRules2 = SearchRuleUtil.searchInList(rules, Rule::getColorsForContains, parentRule.getColorForData());
            if((foundParentRules2 !=null && !foundParentRules2.isEmpty())
                    && !parentRule.getColorForData().equalsIgnoreCase(endColor)) {
                createChildTreeNode(rootChildBoxTreeNode, foundParentRules2, rules, endColor);
                countBoxes++;
            }
        });
    }
}
