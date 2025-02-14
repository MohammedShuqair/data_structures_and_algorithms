package Tree;

import java.util.*;

public class TreeMain {
//    public int[] runningSum(int[] nums) {
//    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5, new TreeNode(10, null, null), new TreeNode(20, null, null));
        BinaryTree binaryTree=new BinaryTree();
        binaryTree.root=node;
        binaryTree.iterativeInsert(30);
        binaryTree.inOrder();
        }
}
