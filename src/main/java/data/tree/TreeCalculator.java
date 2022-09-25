package data.tree;

import calculate.NodeTreeCalculuator;
import data.input.Box;
import data.input.Rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TreeCalculator<T extends Box> {
    public int getNrOfChildren(TreeNode<T> rootNode){
        int nodes = 0;
        for (TreeNode node : rootNode) {
            nodes++;
        }
        return nodes-1;
    }

    public int getNrOfLeaves(TreeNode<T> rootNode){
        int leaves = 0;
        for (TreeNode node : rootNode) {
            if(node.isLeaf()){
                leaves++;
            }
        }
        return leaves;
    }

    public List<String> getUniqueColorBoxes(TreeNode<T> rootNode){
        HashMap<String, Integer> uniqueColorLeaves = new HashMap();
        for (TreeNode node : rootNode) {
            if(!node.isRoot()) {
                String colorBox = node.getData().getColor();
                uniqueColorLeaves.put(colorBox, 1);
            }
        }
        Set<String> uniqueColors = uniqueColorLeaves.keySet();
        return uniqueColors.stream().collect(Collectors.toList());
    }
}