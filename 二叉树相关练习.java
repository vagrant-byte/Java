package 二叉树;

import java.util.*;

public class 二叉树相关练习 {
    //验证是否为搜素二叉树
    //将其中序遍历判断其是否有序
    public boolean isValidBST(TreeNode root) {
        if(root==null) {
            return true;
        }
        TreeNode cur=root;
        Stack<TreeNode> stack=new Stack<>();
        ArrayList<Integer> list=new ArrayList<>();
        while (cur!=null||!stack.isEmpty()) {
            if(cur!=null) {
                stack.push(cur);
                cur=cur.left;
            }else {
                cur=stack.pop();
                list.add(cur.val);
                cur=cur.right;
            }
        }
        for (int i = 0; i <list.size()-1 ; i++) {
            if(list.get(i)>=list.get(i+1)) {
                return false;
            }
        }
        return true;
    }
    //使用一个全局变量用来表示最小值，递归依次比较
    public long tmp=Long.MIN_VALUE;
    public boolean isValidBST1(TreeNode root) {
        if(root==null) {
            return true;
        }
        //判断其左子树是否是搜素二叉树
        boolean flag=isValidBST1(root.left);
        if(!flag) {
            return false;
        }
        if(root.val<=tmp) {
            //即后一个节点的值小于前一个节点的值
            return false;
        }else {
            tmp=root.val;
        }
        //递归判断其右子树是否为搜素二叉数
        return isValidBST1(root.left);
    }
    //搜索二叉树 递归套路实现  左树是搜索二叉树 左树最大最小值，右树搜素二叉树 右树最大最小值
    public class ReturnData {
        public boolean isBST;
        public int min;
        public int max;
        public ReturnData(boolean is,int mi,int ma) {
            isBST=is;
            min=mi;
            max=ma;
        }
    }
    public ReturnData process1(TreeNode root){
        if(root==null) {
            return null;
        }
        ReturnData leftData=process1(root.left);
        ReturnData rightData=process1(root.right);
        boolean isBST;
        int min=root.val;
        int max=root.val;
        if(leftData!=null) {
            min=Math.min(min,leftData.min);
            max=Math.max(max,leftData.max);
        }
        if(rightData!=null) {
            min=Math.min(min,rightData.min);
            max=Math.max(max,rightData.max);
        }
        isBST=true;
        if(leftData!=null&&!leftData.isBST||leftData.max>=root.val) {
            isBST=false;
        }
        if(rightData!=null&&!rightData.isBST||rightData.min>=root.val) {
            isBST=false;
        }

        return new ReturnData(isBST,min,max);
    }
    //完全二叉树判断
    public boolean isCompleteTree(TreeNode root) {
        if(root==null) {
            return true;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        boolean flag=false;//标记第一个左右不全的节点
        while (!queue.isEmpty()) {
            root=queue.poll();
            if((root.left==null&&root.right!=null)||(flag&&(root.left!=null||root.right!=null))) {
                //有右孩子没有左孩子返回false，当出现第一个左右不全的节点后，其后边的节点不是叶子节点返回false
                return false;
            }
            if(root.left!=null) {
                queue.add(root.left);
            }
            if(root.right!=null) {
                queue.add(root.right);
            }
            if(root.right==null) {
                //其左节点不为空，第一个左右不全的节点
                flag=true;
            }
        }
        return true;
    }
    //平衡二叉树
    //即左树是平衡二叉树 右树是平衡二叉 左树高度-右树高度<=1
    public boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }
    public class ReturnType {
        public boolean isBalanced;
        public int height;
        public ReturnType(boolean isB,int hei) {
            isBalanced=isB;
            height=hei;
        }
    }
    private ReturnType process(TreeNode root) {
        if(root==null) {
            //是平衡二叉树，高度为0
            return new ReturnType(true,0);
        }
        ReturnType leftData=process(root.left);
        ReturnType rightData=process(root.right);
        int height=Math.max(rightData.height,leftData.height)+1;//树的深度
        boolean isBalanced=leftData.isBalanced&&rightData.isBalanced&&Math.abs(leftData.height-rightData.height)<2;
        return new ReturnType(isBalanced,height);
    }

    //满二叉树 节点个数=树高度的平方-1
    public boolean isF(TreeNode root) {
        if(root==null) {
            return true;
        }
        Info data=f(root);
        return data.nodes==1<<data.height-1;
    }
    public class Info{
        public int height;//树的高度
        public int nodes;//节点个数
        public Info(int h,int n){
            height=h;
            nodes=n;
        }
    }
    public Info f(TreeNode root) {
        if(root==null) {
            return new Info(0,0);
        }
        Info leftData=f(root.left);
        Info rightData=f(root.right);
        int height=Math.max(leftData.height,rightData.height)+1;
        int nodes=leftData.nodes+rightData.nodes+1;
        return new Info(height,nodes);
    }
    //二叉树的最近公共祖先
    //生成所有节点的父节点表,将p整条链上的父节点记在hashset中，当q的父节点第一次出现在set中即最近的公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) {
            return root;
        }
        HashMap<TreeNode,TreeNode> map=new HashMap<>();
        map.put(root,root);
        process(root,map);
        HashSet<TreeNode> set=new HashSet<>();
        TreeNode cur=p;
        while (cur!=map.get(cur)) {
            set.add(cur);
            cur=map.get(cur);
        }
        cur=q;
        while (cur!=map.get(cur)) {
            if(set.contains(cur)){
                return cur;
            }else {
                cur=map.get(cur);
            }
        }
        return root;
    }
    public void process(TreeNode head,HashMap<TreeNode,TreeNode> map) {
        if(head==null) {
            return;
        }
        map.put(head.left,head);
        map.put(head.right,head);
        process(head.left,map);
        process(head.right,map);
    }
    //后继节点  有右子树为右子树的最左节点，无右子树是其左节点的父节点
   /* public TreeNode getSucessorNode(TreeNode node) {
        if(node==null) {
            return node;
        }
        if(node.right!=null) {
            //有右子树
            return getLeftMost(node.right);
        }else {
            TreeNode parent=node.parent;
            while (parent!=null&&parent.left!=node) {
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }

    private TreeNode getLeftMost(TreeNode node) {
        if(node==null) {
            return null;
        }
        while (node.left!=null) {
            node=node.left;
        }
        return node;
    }*/

    //序列化和反序列化
    public String serialize(TreeNode root) {
        if(root==null) {
            return "#_";
        }
        String res=root.val+"_";
        res+=serialize(root.left);
        res+=serialize(root.right);
        return res;
    }
    public TreeNode deserialize(String data) {
        String[] values=data.split("_");
        Queue<String> queue=new LinkedList<>();
        for(int i=0;i<values.length;i++) {
            queue.add(values[i]);
        }
        //使用前序遍历将其取出
        return reconPreOrder(queue);
    }
    public TreeNode reconPreOrder(Queue<String> queue) {
        String value=queue.poll();
        if(value.equals("#")) {
            return null;
        }
        TreeNode head=new TreeNode(Integer.valueOf(value));
        head.left=reconPreOrder(queue);
        head.right=reconPreOrder(queue);
        return head;
    }
}
