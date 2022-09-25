package data.tree;

import data.input.Box;

public class TreePrinter<T extends Box> {

    public void printTree(TreeNode<T> treeRoot){
        for (TreeNode<T> node : treeRoot) {
            String indent = createIndent(node.getLevel());
            System.out.println(indent + node.getData() + " level: " + node.getLevel());
        }
    }

    private String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
