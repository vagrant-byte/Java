
import java.util.Scanner;
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
public class Day17 {
    //二叉树镜像
    public void Mirror(TreeNode root) {
        if(root==null) {
            return;
        }
        if(root.left==null||root.right==null) {
            return;
        }
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;
        if(root.left!=null) {
            Mirror(root.left);
        }
        if(root.right!=null) {
            Mirror(root.right);
        }

    }
    //杨辉三角的变形
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            if(n<=2) {
                System.out.println(-1);
            }
            if(n%2==1) {
                System.out.println(2);
            }
            if(n%2==0) {
                System.out.println(n/2%2+3);
            }
        }

    }
}
