import java.util.Scanner;

class TreeNode {
    public char val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(char val) {
        this.val=val;
    }
}
public class demo6 {
    //二叉树遍历
    public static int i=0;
    public static TreeNode creat(String s) {
        TreeNode root=null;
        char[] chars=s.toCharArray();
        if(s==null) {
            return null;
        }
        if(chars[i]!='#') {
            root=new TreeNode(chars[i]);
            i++;
            root.left=creat(s);
            root.right=creat(s);
        } else {
            i++;
        }
        return root;
    }
    public static void inorder(TreeNode root) {
        if(root==null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
    //二叉树的最近公共祖先
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        TreeNode root=creat(s);
        inorder(root);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) {
            return null;
        }
        //p或q本身就是根节点
        if(root==p||root==q) {
            return root;
        }
        TreeNode leftTree=lowestCommonAncestor(root.left,p,q);
        TreeNode rightTree=lowestCommonAncestor(root.right,p,q);
        if(leftTree==null) {
            return rightTree;
        }
        if(rightTree==null) {
            return leftTree;
        }
        if(leftTree==null&&rightTree==null) {
            return null;
        }
        return root;

    }

}
