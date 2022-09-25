package build;

import data.input.Box;
import data.input.Rule;
import data.tree.TreeNode;
import exception.ColoredBoxNotFoundException;
import exception.NoRulesException;
import lombok.Data;
import rules.SearchRuleUtil;
import java.util.List;

@Data
public class LoadTreeDesc {
    private static int countBoxes = 0;
    private static int countLeaves = 0;

    public static TreeNode<Box> buildTree(String rootBoxColor, List<Rule<Box>> rules) throws Exception {
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

        Rule rootrule = foundRootRules.get(0);
        List<Box> rootRuleChildren = rootrule.getContains();

        rootRuleChildren.stream().forEach(childRule ->{
            Box rootChildBox = createBox(childRule.getColor());
            List<Rule<Box>> foundChildRules = SearchRuleUtil.search(rules, Rule::getColorForData, childRule.getColor());
            for(int i=0;i<childRule.getAmount();i++) {
                TreeNode<Box> rootChildBoxTreeNode = rootTreeNode.addChild(rootChildBox);
                if(foundChildRules !=null || foundChildRules.size()>0) {
                    createChildTreeNode(rootChildBoxTreeNode, foundChildRules, rules);
                    countBoxes++;
                }
            }
        });

        rootTreeNode.setContains(countBoxes);
        rootTreeNode.setNrOfLeaves(countLeaves);

        return rootTreeNode;
    }
    private static TreeNode<Box> createRootTreeNode(String rootBoxColor){
        Box rootBox = createBox(rootBoxColor);
        TreeNode<Box> rootTreeNode = new TreeNode<>(rootBox);
        return rootTreeNode;
    }
    private static Box createBox(String boxColor){
        Box box = new Box();
        box.setColor(boxColor);
        box.setAmount(1);
        return box;
    }
    private static void createChildTreeNode( TreeNode<Box> treeNode,
                                             List<Rule<Box>> childRules,
                                             List<Rule<Box>> rules){
        if(childRules == null || childRules.size()==0){
            return;
        }

        Rule childrule = childRules.get(0);
        List<Box> ruleChildren = childrule.getContains();

        ruleChildren.stream().forEach(ruleChild ->{
            Box childBox = createBox(ruleChild.getColor());
            List<Rule<Box>> foundChildRules = SearchRuleUtil.search(rules, Rule::getColorForData, ruleChild.getColor());
            for(int i=0;i<ruleChild.getAmount();i++) {
                TreeNode<Box> childBoxTreeNode = treeNode.addChild(childBox);
                if(foundChildRules !=null || foundChildRules.size()>0) {
                    createChildTreeNode(childBoxTreeNode, foundChildRules, rules);
                    countBoxes++;
                }else{
                    countLeaves++;
                }
            }
        });
    }
}
