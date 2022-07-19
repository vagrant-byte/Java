package 二叉树;

import com.sun.prism.shader.AlphaOne_LinearGradient_AlphaTest_Loader;

public class 二叉搜素数的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) {
            return null;
        }
        if((p.val>=root.val&&q.val<=root.val)||(p.val<=root.val&&q.val>=root.val)) {
            return root;
        }
        if(p.val<root.val&&q.val<root.val) {
            return lowestCommonAncestor(root.left,p,q);
        }
        if(p.val>root.val&&q.val>root.val) {
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }
}
