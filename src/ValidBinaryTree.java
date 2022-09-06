public class ValidBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        System.out.println(isValidBST(root));
    }

    private static boolean isValidBST(TreeNode root) {
        if(root == null || root.left == null || root.right == null)
            return true;
        if(root.val < root.left.val || root.val > root.right.val)
            return false;
        return isValidBST(root.left) && isValidBST(root.right);
    }
}
