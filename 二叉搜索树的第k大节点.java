package 二叉树;

import java.util.ArrayList;
import java.util.Comparator;

public class 二叉搜索树的第k大节点 {
    public int kthLargest(TreeNode root, int k) {
        ArrayList<Integer> list=new ArrayList<>();
        InorderTraversal(root,list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        return list.get(k-1);
    }

    private void InorderTraversal(TreeNode root, ArrayList<Integer> list) {
        if(root==null) {
            return;
        }
        InorderTraversal(root.left,list);
        list.add(root.val);
        InorderTraversal(root.right,list);
    }
}
