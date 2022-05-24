public class isUnivalTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //单值二叉树
    public boolean isUnivalTree(TreeNode root) {
        if(root==null) {
            return true;
        }
        if(root.left!=null&&root.left.val!=root.val) {
            return false;
        }
        if(root.right!=null&&root.right.val!=root.val) {
            return false;
        }
        return isUnivalTree(root.left)&&isUnivalTree(root.right);
    }
}
