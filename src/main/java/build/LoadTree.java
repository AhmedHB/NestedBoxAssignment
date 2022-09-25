package build;

import data.input.Box;
import data.tree.TreeNode;

public class LoadTree {
    protected static int countBoxes = 0;
    protected static int countLeaves = 0;
    protected static TreeNode<Box> createRootTreeNode(String rootBoxColor){
        Box rootBox = createBox(rootBoxColor);
        return new TreeNode<>(rootBox);
    }
    protected static Box createBox(String boxColor){
        Box box = new Box();
        box.setColor(boxColor);
        box.setAmount(1);
        return box;
    }
}
