package 算法第一天;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreeNode2 {
    int val = 0;
    算法第一天.TreeNode2 left = null;
    算法第一天.TreeNode2 right = null;

    public TreeNode2(int val) {
        this.val = val;

    }
}
public class Demo6 {
    //二叉搜索树的第K个节点
    TreeNode2 KthNode(TreeNode2 pRoot, int k) {
        if(pRoot==null||k<=0) {
            return null;
        }
        TreeNode2 cur=pRoot;
        //中序遍历二叉树
        Stack<TreeNode2> s=new Stack<>();
        do {
            while (cur!=null) {
                s.push(cur);
                cur=cur.left;
            }
            if (!s.empty()) {
                cur=s.pop();
                k--;
                if(k==0) {
                    return cur;
                }
                cur=cur.right;
            }
        }while (cur!=null||!s.empty());
        return null;

    }

    //按之字形顺序打印二叉树
    //从左向右入元素那么下层也是从左向右入栈
    public ArrayList<ArrayList<Integer>> Print(TreeNode2 pRoot) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        if(pRoot==null) {
            return result;
        }
        Stack<TreeNode2> stack=new Stack<>();
        Queue<TreeNode2> queue=new LinkedList<>();
        stack.push(pRoot);
        ArrayList<Integer> list=new ArrayList<>();
        int dur=1;//dur==1表示从左向右 dur==2表示从右向左
        while (!stack.empty()) {
            int size=stack.size();
            for (int i = 0; i <size ; i++) {
                TreeNode2 cur=stack.pop();
                list.add(cur.val);
                TreeNode2 first=(dur==1)?cur.left:cur.right;
                TreeNode2 second=(dur==1)?cur.right:cur.left;
                if(first!=null) {
                    queue.offer(first);
                }
                if(second!=null) {
                    queue.offer(second);
                }
            }
            result.add(new ArrayList<>(list));
            list.clear();
            while (!queue.isEmpty()) {
                stack.push(queue.poll());
            }
            dur=(dur==1)?2:1;
        }
        return result;
    }

    //反转单词序列
    private void reverse(char[] chars,int start,int end) {
        while (start<end) {
            char tmp=chars[start];
            chars[start]=chars[end];
            chars[end]=tmp;
            start++;
            end--;
        }
    }
    public String ReverseSentence(String str) {
        if(str==null||str.length()==0) {
            return str;
        }
        int i=0;
        int j=i;
        char[] chars=str.toCharArray();
        while (i<chars.length) {
            //没遇到空格时一直往前走
            while (i<chars.length&&!Character.isSpaceChar(chars[i])) {
                i++;
            }
            //遇到空格后逆置
            reverse(chars,j,i-1);
            //遇到多个空格情况
            while (i<chars.length&&Character.isSpaceChar(chars[i])) {
                i++;
            }
            j=i;
        }
        //对最后一个单词进行逆置
        reverse(chars,j,i-1);
        //对整体进行逆置
        reverse(chars,0,chars.length-1);
        return new String(chars);

    }
}
