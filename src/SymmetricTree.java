public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        SymmetricTree s = new SymmetricTree();
        System.out.println(s.isSymmetric(root));
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        if((left == null && right != null) || (left != null && right == null))
            return false;
        return left.val == right. val && helper(left.left, right.right);

    }
}
