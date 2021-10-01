package 算法第一天;

import java.util.*;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
    public class Demo1 {
        //实现一个最小栈
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int node) {
            stack.push(node);
            if (minStack.empty()) {
                minStack.push(node);
            } else {
                int tmp = minStack.peek();
                if (node > tmp) {
                    minStack.push(tmp);
                } else {
                    minStack.push(node);
                }
            }
        }

        //弹出栈顶元素
        public void pop() {
            stack.pop();
            minStack.pop();

        }

        //获取栈顶元素
        public int top() {
            return stack.peek();
        }

        //获取栈中最小元素
        public int min() {
            return minStack.peek();
        }

        //栈的压入弹出序列
        public boolean IsPopOrder(int[] pushA, int[] popA) {
            if (pushA == null || popA == null || pushA.length != popA.length) {
                return false;
            }
            Stack<Integer> stack = new Stack<>();
            int j = 0;
            for (int i = 0; i < pushA.length; i++) {
                stack.push(pushA[i]);
                while (!stack.empty() && stack.peek() == popA[j]) {
                    stack.pop();
                    j++;
                }
            }
            return stack.empty();
        }

        //二叉树层次遍历
        public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
            //使用队列实现
            //创建一个数组存储返回值
            ArrayList<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            //根节点入队列
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.peek();
                //将队列中元素弹出，放入数组中
                queue.poll();
                result.add(node.val);
                //左子树入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                //右子树入队列
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return result;
        }
        //二叉搜素数的后序遍历
        public boolean VerifySquenceOfBST(int [] sequence) {
            

        }

    }

}

