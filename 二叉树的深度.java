package 二叉树;

public class 二叉树的深度 {
    public int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return Math.max((maxDepth(root.left)+1),(maxDepth(root.right)+1));
    }
}
