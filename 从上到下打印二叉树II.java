package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 从上到下打印二叉树II {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root!=null) {
            queue.add(root);
        }
        int count=0;
        while (!queue.isEmpty()) {
            List<Integer> list1=new ArrayList<>();
            for (int i = queue.size(); i >0 ; i--) {
                TreeNode cur=queue.poll();
                list1.add(cur.val);
                if(cur.left!=null) {
                    queue.add(cur.left);
                }
                if(cur.right!=null) {
                    queue.add(cur.right);
                }
            }
            count=count+1;
            if(count%2==0) {
                list1=revous(list1);
            }
            list.add(list1);
        }
        return list;

    }

    private List<Integer> revous(List<Integer> list1) {
        List<Integer> list11=new ArrayList<>();
        for (int i = list1.size()-1; i >=0 ; i--) {
            list11.add(list1.get(i));
        }
        return list11;
    }
}
