package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class 从上到下打印二叉树1 {
    public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root!=null) {
            queue.add(root);
        }
        int[] tmp=new int[1001];
        int count=0;
        while (!queue.isEmpty()) {
            TreeNode cur=queue.poll();
            tmp[count++]=cur.val;
            if(cur.left!=null) {
                queue.add(cur.left);
            }
            if(cur.right!=null) {
                queue.add(cur.right);
            }
        }
        int[] res=new int[count];
        for (int i = 0; i <count ; i++) {
            res[i]=tmp[i];
        }
        return res;
    }

}
