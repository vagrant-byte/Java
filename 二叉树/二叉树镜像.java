package 二叉树;

public class 二叉树镜像 {
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode newRoot=new TreeNode(root.val);
        newRoot.left=mirrorTree(root.right);
        newRoot.right=mirrorTree(root.left);
        return newRoot;
    }
}
