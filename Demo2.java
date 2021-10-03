package 算法第一天;

import java.util.ArrayList;
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
public class Demo2 {
        //二叉树中和为某一值的路径  简单回溯
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int expectNumber) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        if(root==null) {
            return result;
        }
        ArrayList<Integer> list=new ArrayList<>();
        FindPathDFS(root,expectNumber,result,list);
        return result;
    }
    public void FindPathDFS(TreeNode root,int expectNumber,ArrayList<ArrayList<Integer>> result,ArrayList<Integer> list) {
        if(root==null) {
            return;
        }
        list.add(root.val);
        expectNumber-=root.val;
        if (expectNumber==0&&root.left==null&&root.right==null) {
            result.add(new ArrayList<>(list));
        }
        FindPathDFS(root.left,expectNumber,result,list);
        FindPathDFS(root.right,expectNumber,result,list);
        list.remove(list.size()-1);
    }

    }
}
