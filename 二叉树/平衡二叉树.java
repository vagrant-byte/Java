package 二叉树;

public class 平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        if(root==null) {
            return true;
        }
        int leftDepth=maxDepth(root.left);
        int rightDepth=maxDepth(root.right);
        return isBalanced(root.left)&&isBalanced(root.right)&&(Math.abs(leftDepth-rightDepth)<=1);

    }

    private int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftDepth=maxDepth(root.left);
        int rightDepth=maxDepth(root.right);
        return Math.max(rightDepth,leftDepth)+1;
    }
}
