package 二叉树;

import java.util.Arrays;

class TreeNode1 {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode1(int x) { val = x; }
 }
public class 重建二叉树 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0) {
            return null;
        }

        int rootVal=preorder[0];
        int rootIndex=0;
        for (int i = 0; i <inorder.length ; i++) {
            if(inorder[i]==rootVal) {
                rootIndex=i;
                break;
            }
        }
        TreeNode root=new TreeNode(preorder[0]);
        root.left=buildTree(Arrays.copyOfRange(preorder,1,rootIndex+1),Arrays.copyOfRange(inorder,0,rootIndex+1));
        root.right=buildTree(Arrays.copyOfRange(preorder,rootIndex+1,preorder.length),Arrays.copyOfRange(inorder,rootIndex+1,inorder.length));
        return root;
    }
}
