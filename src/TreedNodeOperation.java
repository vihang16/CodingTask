import java.util.Arrays;
import java.util.Comparator;

public class TreedNodeOperation {

    public static void main(String[] args) {
        TreedNodeOperation to = new TreedNodeOperation();
        TreeNode n = new TreeNode(1);
        n.left = new TreeNode(2);
        n.left.right = new TreeNode(3);
        n.right = new TreeNode(4);
        System.out.println(to.heightWithDeletion(n, 4));
        System.out.println(to.heightWithDeletion(n, 2));
        int[][] intervals = new int[5][0];

    }
/*
    public int height() {
        return height(root);
    }*/

    private int height(TreeNode node) {
        if(node == null)
            return 0;

        return 1 + Math.max(height(node.left), height(node.right));
    }

    //public int heightWithDeletion(int val) { return heightWithDeletion(root, val); }

    private int heightWithDeletion(TreeNode node, int val) {
        if(node == null)
            return 0;

        if(node.val == val) {
            return 0;
        }

        return 1 + Math.max(heightWithDeletion(node.left, val), heightWithDeletion(node.right, val));
    }
}
