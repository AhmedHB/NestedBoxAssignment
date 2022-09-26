package calculate;

import build.LoadTreeAsc;
import build.LoadTreeDesc;
import data.input.Box;
import data.input.Rule;
import data.tree.TreeCalculator;
import data.tree.TreeNode;

import java.util.List;

public class NodeTreeCalculator {

    private final List<Rule<Box>> rules;
    private TreeNode<Box> rootNodeTree;
    public NodeTreeCalculator(List<Rule<Box>> rules){
        this.rules = rules;
    }

    public void buildTreeDescWithUsingColorBoxAmount(String rootBoxColor) throws Exception{
        rootNodeTree = LoadTreeDesc.buildTreeWithUsingColorBoxAmount(rootBoxColor, rules);
    }

    public void buildTreeAscWithoutUsingColorBoxAmount(String rootBoxColor, String boxColor) throws Exception{
        rootNodeTree = LoadTreeAsc.buildTreeWithoutUsingColorBoxAmount(rootBoxColor, boxColor, rules);
    }

    public int getNrOfChildBoxes(){
        TreeCalculator<Box> calc = new TreeCalculator<>();
        return calc.getNrOfChildren(rootNodeTree);
    }

    public int getNrOfLeaveBoxes(){
        TreeCalculator<Box> calc = new TreeCalculator<>();
        return calc.getNrOfLeaves(rootNodeTree);
    }

    public List<String> getUnqieueColorBoxes(){
        TreeCalculator<Box> calc = new TreeCalculator<>();
        return calc.getUniqueColorBoxes(rootNodeTree);
    }
}
