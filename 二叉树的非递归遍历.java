package 二叉树;

import java.util.*;

//public class TreeNode {
//    int val;
//    二叉树.TreeNode left;
//    二叉树.TreeNode right;
//
//    TreeNode() {
//    }
//    TreeNode(int val) {
//        this.val = val;
//    }
//
//    TreeNode(int val, 二叉树.TreeNode left, 二叉树.TreeNode right) {
//        this.val = val;
//        this.left = left;
//        this.right = right;
//    }
//}
public class 二叉树的非递归遍历 {
    //前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if(root==null) {
            return list;
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            root=stack.pop();
            list.add(root.val);
            if(root.right!=null) {
                stack.push(root.right);
            }
            if(root.left!=null) {
                stack.push(root.left);
            }
        }
        return list;
    }
    //中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if(root==null) {
            return list;
        }
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        while(!stack.isEmpty()||cur!=null) {
            if(cur!=null) {
                stack.push(cur);
                cur=cur.left;
            }else {
                cur=stack.pop();
                list.add(cur.val);
                cur=cur.right;
            }
        }
        return list;
    }
    //后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if(root==null) {
            return list;
        }
        Stack<TreeNode> s1=new Stack<>();
        Stack<TreeNode> s2=new Stack<>();
        s1.push(root);
        while(!s1.isEmpty()) {
            root=s1.pop();
            s2.push(root);
            if(root.left!=null) {
                s1.push(root.left);
            }
            if(root.right!=null) {
                s1.push(root.right);
            }
        }
        while(!s2.isEmpty()) {
            list.add(s2.pop().val);
        }
        return list;
    }
    //层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list=new ArrayList<>();
        if(root==null) {
            return list;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size=queue.size();
            List<Integer> list1=new ArrayList<>();
            while (size>0) {
                root=queue.poll();
                list1.add(root.val);
                if(root.left!=null) {
                    queue.add(root.left);
                }
                if(root.right!=null) {
                    queue.add(root.right);
                }
                size--;
            }
            list.add(list1);
        }
        return list;
    }
}
