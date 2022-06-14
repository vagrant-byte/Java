package 二叉树;

import java.util.*;

public class TreeNode {
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
    //非递归二叉树前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list1=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        while (cur!=null||!stack.isEmpty()) {
            while (cur!=null) {
                stack.push(cur);
                list1.add(cur.val);
                cur=cur.left;
            }
            TreeNode top=stack.pop();
            cur=top.right;
        }
        return list1;

    }
    //非递归实现中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list1=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        while (cur!=null||!stack.isEmpty()) {
            while (cur!=null) {
                stack.push(cur);
                cur=cur.left;
            }
            TreeNode top=stack.pop();
            list1.add(top.val);
            cur=top.right;
        }
        return list1;
    }
    //非递归实现 二叉树的后续遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list1=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        TreeNode cur=root;
        TreeNode prev=null;
        while (cur!=null||!stack.isEmpty()) {
            while (cur!=null) {
                stack.push(cur);
                cur=cur.left;
            }
            TreeNode top=stack.peek();
            //如果当前节点的右子树被打印过或者遍历过直接弹出
            if(top.right==null||top.right==prev) {
                stack.pop();
                list1.add(top.val);
                prev=top;//记录最近一次的打印节点
            }else {
                cur=top.right;
            }
        }
        return list1;
    }
    //求结点的个数
    public int getSize(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int left=getSize(root.left);
        int right=getSize(root.right);
        return left+right+1;

    }
    //求叶子结点的个数
    public int getLeafNodeCount(TreeNode root) {
        if(root==null) {
            return 0;
        }
        if(root.left==null&&root.right==null) {
            return 1;
        }
        return getLeafNodeCount(root.left)+getLeafNodeCount(root.right);
    }
    //第k层结点的个数
    public int getKLevelNodeCount(TreeNode root,int k) {
        if(root==null||k<=0) {
            return 0;
        }
        if(k==1) {
            return 1;
        }
        return getKLevelNodeCount(root.left,k-1)+getKLevelNodeCount(root.right,k-1);

    }
    //树的高度
    public int getHeight(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftHeight=getHeight(root.left);
        int rightHeight=getHeight(root.right);
        return Math.max(rightHeight,leftHeight)+1;
    }
    //查找元素
    public TreeNode find(TreeNode root,int val) {
        if(root==null) {
            return null;
        }
        if(root.val==val) {
            return root;
        }
        TreeNode ret=find(root.left,val);
        if(ret!=null) {
            return ret;
        } else {
            ret=find(root.right,val);
            if(ret!=null) {
                return ret;
            }
        }
        return null;
    }
    //判断是否是完全二叉树
    public boolean isCompleteTree(TreeNode root) {
        if(root==null) {
            return true;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode ret=queue.poll();
            if(ret!=null) {
                queue.offer(ret.left);
                queue.offer(ret.right);
            } else {
                break;
            }
        }
        while (!queue.isEmpty()) {
            if(queue.poll()!=null) {
                return false;
            }
        }
        return true;
    }
    //二叉树深度
    public int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        int depth=0;
        //使用层序遍历
        while (!queue.isEmpty()) {
            int size=queue.size();//队列元素中个数
            depth++;
            for (int i = 0; i <size ; i++) {
                TreeNode cur=queue.poll();
                if(cur.left!=null) {
                    queue.offer(cur.left);
                }
                if(cur.right!=null) {
                    queue.offer(cur.right);
                }
            }
        }
        return depth;
    }
    //二叉搜素树中的插入操作
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node=new TreeNode();
        node.val=val;
        if(root==null) {
            root=node;
            return root;
        }
        TreeNode current=root;
        TreeNode parent=null;
        while (current!=null) {
            if(current.val==val) {
                throw new RuntimeException("包含重复的数值，插入失败");
            }
            if(current.val<val) {
                parent=current;
                current=current.right;
            }
            if(current.val>val) {
                parent=current;
                current=current.left;
            }
        }
        //parent 表示要插入位置的父节点
        if(val<parent.val) {
            parent.left=node;
        } else {
            parent.right=node;
        }
        return root;
    }
    //二叉搜索树中第k小元素
    public int kthSmallest(TreeNode root, int k) {
        if(root==null||k<=0) {
            return 0;
        }
        Stack<TreeNode> stack=new Stack<>();
        TreeNode node=root;
        do {
            while (node!=null) {
                stack.push(node);
                node=node.left;
            }
            if(!stack.isEmpty()) {
                node=stack.pop();
                k--;
                if(k==0) {
                    return node.val;
                }
                node=node.right;
            }
        }while (node!=null||!stack.isEmpty());
        return 0;

    }
    //二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) {
            return root;
        }
        if(root==p||root==q) {
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if (left != null && right != null) {
            // p q 一个在左，一个在右
            return root;
        }
        if (left != null) {
            // p q 都在左子树
            return left;
        }
        if (right != null) {
            // p q 都在右子树
            return right;
        }
        return null;
    }
    //最近公共祖先栈实现
    public boolean getPath(TreeNode root,TreeNode node,Stack<TreeNode> stack) {
        if(root==null||node==null) {
            return false;
        }
        stack.push(root);
        if(root==node) {
            return true;
        }
        boolean flg=getPath(root.left,node,stack);
        if(flg) {
            return true;
        }
        flg=getPath(root.right,node,stack);
        if(flg) {
            return true;
        }
        stack.pop();
        return false;
    }
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) {
            return null;
        }
        Stack<TreeNode> stack1=new Stack<>();
        getPath(root,p,stack1);
        Stack<TreeNode> stack2=new Stack<>();
        getPath(root,q,stack2);
        int size1=stack1.size();
        int size2=stack2.size();
        if(size1-size2>0) {
            int size=size1-size2;
            while (size!=0) {
                stack1.pop();
                size--;
            }
            while (!stack1.isEmpty()&&!stack2.isEmpty()) {
                if(stack1.peek()==stack2.peek()) {
                    return stack1.pop();
                }else {
                    stack1.pop();
                    stack2.pop();
                }
            }
        } else {
            int size=size2-size1;
            while (size!=0) {
                stack2.pop();
                size--;
            }
            while (!stack1.isEmpty()&&!stack2.isEmpty()) {
                if(stack1.peek()==stack2.peek()) {
                    return stack1.pop();
                }else {
                    stack1.pop();
                    stack2.pop();
                }
            }
        }
        return null;
    }

    //后继者
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root==null) {
            return null;
        }
        if(root.val<=p.val) {
            return inorderSuccessor(root.right,p);
        }
        TreeNode node=inorderSuccessor(root.left,p);
        return node==null?root:node;
    }
    //从前序与中序遍历序列构造二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||inorder==null) {
            return null;
        }
        return reConstructBinaryTreeCore(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    private TreeNode reConstructBinaryTreeCore(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if(preStart>preEnd||inStart>inEnd) {
            return null;
        }
        TreeNode root=new TreeNode(preorder[preStart]);
        //在中序序列中找出根节点，将数组分为两部分
        for (int i = inStart; i <=inEnd ; i++) {
            if(inorder[i]==preorder[preStart]) {
                //根据中序，我们确认左子树的节点个数是i-inStart，所有需要从preStart+1，连续i-inStart个元素就是左子树的前序序列
                root.left=reConstructBinaryTreeCore(preorder,preStart+1,i-inStart+preStart,inorder,inStart,i-1);
                root.right=reConstructBinaryTreeCore(preorder,i-inStart+preStart+1,preEnd,inorder,i+1,inEnd);
                break;
            }
        }
        return root;
    }
    //树的子结构
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //空树不是任意一个树的子结构
        if(A==null||B==null) {
            return false;
        }
        boolean flg=false;
        if(A.val==B.val) {
            //判断其是否相同
            flg=isSameChild(A,B);
        }
        if(flg!=true) {
            //判断左子树是否包含树的子结构
            flg=isSubStructure(A.left,B);
        }
        if(flg!=true) {
            //判断右子树是否包含树的子结构
            flg=isSubStructure(A.right,B);
        }
        return flg;
    }

    private boolean isSameChild(TreeNode begin, TreeNode beginSub) {
        if(beginSub==null) {
            //说明比较完了
            return true;
        }
        if(begin==null) {
            //begin为空，说明beginsub不是你的子树
            return false;
        }
        if(begin.val!=beginSub.val) {
            return false;
        }
        return isSameChild(begin.left,beginSub.left)&&isSameChild(begin.right,beginSub.right);
    }
    //二叉树镜像
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
    //二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list=new ArrayList<>();
        if(root==null) {
            return list;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size=queue.size();
            ArrayList<Integer> list1=new ArrayList<>();
            for (int i = 0; i <size ; i++) {
                TreeNode treeNode=queue.poll();
                list1.add(treeNode.val);
                if(treeNode.left!=null) {
                    levelOrder(root.left);
                }
                if(treeNode.right!=null) {
                    levelOrder(treeNode.right);
                }
            }
            list.add(list1);
        }
        return list;
    }
    //二叉搜素树的后序遍历序列
    public boolean verifyPostorder(int[] postorder) {
        if(postorder.length==0) {
            return true;
        }
        return VerifySquenceOfBSTCore(postorder,0,postorder.length-1);
    }

    private boolean VerifySquenceOfBSTCore(int[] postorder, int start, int end) {
        if(start>end) {
            return true;
        }
        //拿到root节点
        int root=postorder[end];
        //遍历找到左子树
        int i=start;
        while (i<end&&postorder[i]<root) {
            i++;
        }
        //遍历右子树看是否符合条件，所有值都大于root
        for (int j = i; j <end ; j++) {
            if(postorder[j]<root) {
                return false;
            }
        }
        return VerifySquenceOfBSTCore(postorder,start,i-1)&&VerifySquenceOfBSTCore(postorder,i,end-1);

        /*从根节点到叶子节点的路径数字之和
        public int sumNumbers(TreeNode root) {

            int sum= dfs(root,0);
            return sum;
        }
        public int dfs(TreeNode root,int num) {
            if(root==null) {
                return 0;
            }
            num=num*10+root.val;
            if(root.left==null&&root.right==null) {
                return num;
            }
            int leftsum=dfs(root.left,num);
            int rightsum=dfs(root.right,num);
            return leftsum+rightsum;
        }

       */
    }
}
