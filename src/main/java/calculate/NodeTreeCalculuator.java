package calculate;

import build.LoadTreeAsc;
import build.LoadTreeDesc;
import data.input.Box;
import data.input.Rule;
import data.tree.TreeCalculator;
import data.tree.TreeNode;
import data.tree.TreePrinter;

import java.util.List;

public class NodeTreeCalculuator {

    private List<Rule<Box>> rules;
    private TreeNode<Box> rootNodeTree;
    public NodeTreeCalculuator(List<Rule<Box>> rules){
        this.rules = rules;
    }

    public void buildTreeDesc(String rootBoxColor) throws Exception{
        TreeCalculator calc = new TreeCalculator();
        rootNodeTree = LoadTreeDesc.buildTree(rootBoxColor, rules);
    }

    public void buildTreeAsc(String rootBoxColor, String boxColor) throws Exception{
        TreeCalculator calc = new TreeCalculator();
        rootNodeTree = LoadTreeAsc.buildTree(rootBoxColor, boxColor, rules);
    }

    public int getNrOfChildBoxes(){
        TreeCalculator calc = new TreeCalculator();
        int nrOfBoxes = calc.getNrOfChildren(rootNodeTree);
        return nrOfBoxes;
    }

    public int getNrOfLeaveBoxes(){
        TreeCalculator calc = new TreeCalculator();
        int nrOfBoxes = calc.getNrOfLeaves(rootNodeTree);
        return nrOfBoxes;
    }

    public List<String> getUnqieueColorBoxes(){
        TreeCalculator calc = new TreeCalculator();
        List<String> uniqueBoxColors = calc.getUniqueColorBoxes(rootNodeTree);

        /*TreePrinter tp = new TreePrinter();
        tp.printTree(rootNodeTree);*/
        return uniqueBoxColors;
    }
}
