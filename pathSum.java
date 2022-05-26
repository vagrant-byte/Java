package 回溯;

import java.util.ArrayList;
import java.util.List;

//二叉树中和为某一值的路基
//回溯实现
//1.先添加值（待选结果）
//2.在判定现有结果是否满足条件(是 添加到结果集)
//3.DFS 深度优先
//4.回退
public class pathSum {
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
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> list=new ArrayList<>();
        if(root==null) {
            return list;
        }
        List<Integer> list1=new ArrayList<>();
        findpathDFS(list,list1,root,target);
        return list;
    }

    private void findpathDFS(List<List<Integer>> list, List<Integer> list1, TreeNode root, int target) {
        if(root==null) {
            return;
        }
        //将结果放入待选集
        list1.add(root.val);
        target-=root.val;
        if(root.left==null&&root.right==null&&target==0) {
            //符合条件 将结果放入结果集
            list.add(new ArrayList<>(list1));
        }
        findpathDFS(list,list1,root.left,target);
        findpathDFS(list,list1,root.right,target);
        //回退
        list1.remove(list1.size()-1);
    }
}
