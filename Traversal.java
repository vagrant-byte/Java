import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

class TreeNode {
    public char val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(char val) {
        this.val = val;
    }
}

public class Traversal {
    //构造树
    public TreeNode createTree() {
        TreeNode A=new TreeNode('A');
        TreeNode B=new TreeNode('B');
        TreeNode C=new TreeNode('C');
        TreeNode D=new TreeNode('D');
        TreeNode E=new TreeNode('E');
        TreeNode F=new TreeNode('F');
        TreeNode G=new TreeNode('G');
        TreeNode H=new TreeNode('H');
        A.right=C;
        A.left=B;
        B.right=E;
        B.left=D;
        C.right=G;
        C.left=F;
        E.right=H;
        return A;
    }
    // 先序遍历
    void preOrderTraversal(TreeNode root){
        List<Integer> list=new ArrayList<>();
        if (root==null) {
            return;
        }
        System.out.print(root.val+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }
    // 中序遍历
    void inOrderTraversal(TreeNode root) {
        if (root==null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.val+" ");
        inOrderTraversal(root.right);
    }
    // 后序遍历
    void postOrderTraversal(TreeNode root) {
        if (root==null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val+" ");
    }
    // 遍历思路-求结点个数
    static int size = 0;
    void getSize1(TreeNode root) {
        if (root==null) {
            return;
        }
        size++;
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
    // 子问题思路-求结点个数
    int getSize2(TreeNode root) {
        if (root==null) {
            return 0;
        }
        return getSize2(root.right)+getSize2(root.left)+1;
    }
    //求叶子节点数
    int getLeafSize(TreeNode root) {
        if(root==null) {
            return 0;
        }
        if(root.left==null&&root.right==null) {
            return 1;
        }
        return getLeafSize(root.left)+getLeafSize(root.right);

    }
//第K层节点数
    int getLevelSize(TreeNode root,int k) {
        if(root==null||k<1) {
            return 0;
        }
        if(k==1) {
            return 1;
        }
        return getLevelSize(root.left,k-1)+getLevelSize(root.right,k-1);
    }
    //查找val值所在的节点
    TreeNode find(TreeNode root, char val) {
        if(root==null) {
            return null;
        }
        if(root.val==val) {
            return root;
        }
        TreeNode result=find(root.left,val);
        if(result!=null) {
            return result;
        }
        return find(root.right,val);
    }
    //相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //p q都为null
        //p null q!=null 或 p!=null q=null
        //p q 都不为null
        if(p==null&&q==null) {
            return true;
        }
        if(p==null||q==null) {
            return false;
        }
        if(p.val!=q.val) {
            return false;
        }
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);

    }
    //另一个树子树
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null&&subRoot==null) {
            return true;
        }
        if(root==null||subRoot==null) {
            return false;
        }
        boolean ret=false;
        if(root.val==subRoot.val) {
            ret=isSameTree(root,subRoot);
        }
        return ret||isSubtree(root.left,subRoot)||isSubtree(root.right,subRoot);
    }
    //二叉树深度
    public int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        if(root.left==null&&root.right==null) {
            return 1;
        }
        int leftDepth=maxDepth(root.left);
        int rightDepth=maxDepth(root.right);
        return 1+(leftDepth>rightDepth?leftDepth:rightDepth);

    }
    //最小深度
    public int minDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        if(root.left==null&&root.right==null) {
            return 1;
        }
        int leftDepth=minDepth(root.left);
        int rightDepth=minDepth(root.right);
        //左子树为空 为右子树深度加一
        if(leftDepth==0) {
            return rightDepth+1;
        }
        //右子树为空  为左子树深度加一
        if(rightDepth==0) {
            return leftDepth+1;
        }
        //左右子树都不为空，左右子树中最小深度加一
        return 1+(rightDepth>leftDepth?leftDepth:rightDepth);
    }
    //平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root==null) {
            return true;
        }
        if(root.left==null&&root.right==null) {
            return true;
        }
        int leftDepth=maxDepth(root.left);
        int rightDepth=maxDepth(root.right);
        if(leftDepth-rightDepth>1||rightDepth-leftDepth>1) {
            return false;
        }
        return isBalanced(root.left)&&isBalanced(root.right);

    }
    //平衡二叉树 时间复杂度为o(n)
    public int hight(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int leftDepth=hight(root.left);
        int rightDepth=hight(root.right);
        if(leftDepth>=0&&rightDepth>=0&&Math.abs(leftDepth-rightDepth)<=1) {
            return Math.max(leftDepth,rightDepth)+1;
        }else {
            return -1;
        }

    }
    public boolean isBalanced1(TreeNode root) {
        return hight(root)>=0;

    }
    //对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if(root==null) {
            return true;
        }
        return isMirror(root.left,root.right);

    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null) {
            return true;
        }
        if(t1==null||t2==null) {
            return false;
        }
        if(t1.val!=t2.val) {
            return false;
        }
        return isMirror(t1.left,t2.right)&&isMirror(t1.right,t2.left);
    }
    //层序遍历  用队列进行存储  分别将左右子树入队，然后依次出队
    void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);//存放树的根节点
        while (!queue.isEmpty()) {
            TreeNode cur=queue.poll();//根节点出队
            System.out.print(cur.val+" ");
            if(cur.left!=null) {
                //左子树入队
                queue.offer(cur.left);
            }
            if(cur.right!=null) {
                //右子树入队
                queue.offer(cur.right);
            }
        }
    }
    //力扣二叉树分层遍历
    static List<List<Character>> result=new ArrayList<>();
    public List<List<Character>> levelOrder(TreeNode root) {
        if(root==null) {
            return result;
        }
        helper(root,0);
        return result;
    }

    private void helper(TreeNode root, int index) {
        if(index==result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(index).add(root.val);
        if(root.left!=null) {
            helper(root.left,index+1);
        }
        if(root.right!=null) {
            helper(root.right,index+1);
        }
    }
    //判断一棵树是否为完全二叉树
    boolean isCompleteTree(TreeNode root) {
        if(root==null) {
            return true;
        }
        //层序遍历二叉树
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        boolean isSecondStop=false;
        while (queue.isEmpty()) {
            TreeNode cur=queue.poll();
            if(!isSecondStop) {
                //第一阶段
                if(cur.left!=null&&cur.right!=null) {
                    //左右子树都不为空，入队
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                } else if(cur.left==null&&cur.right!=null) {
                    //左子树为空 右子树不为空肯定不是完全二叉树
                    return false;
                } else if(cur.left!=null&&cur.right==null) {
                    //左子树不为空 右子树为空进入第二阶段
                    isSecondStop=true;
                    queue.offer(cur.left);
                } else {
                    //左右子树都为空，进入第二阶段
                    isSecondStop=true;
                }
            } else {
                //第二阶段  要求左右子树都为空
                if(cur.left!=null||cur.right!=null) {
                    return false;
                }
            }
        }
        //整个树遍历完，是完全二叉树
        return true;
    }
    //镜像对称二叉树
    public boolean isSymmetricChild(TreeNode leftTree,TreeNode rightTree) {
        if(leftTree==null||rightTree==null) {
            return false;
        }
        if(leftTree.val!=rightTree.val) {
            return false;
        }
        if(leftTree==null&&rightTree==null){
            return true;
        }
        return isSymmetricChild(leftTree.left,leftTree.right)&&isSymmetricChild(leftTree.right,leftTree.left);
    }
    public boolean isSymmetric1(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isSymmetricChild(root.left,root.right);
    }
    //二叉树的镜像
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if(pRoot==null) {
            return pRoot;
        }
        if(pRoot.right==null&&pRoot.left==null){
            return pRoot;
        }
        TreeNode tmp=pRoot.left;
        pRoot.left=pRoot.right;
        pRoot.right=tmp;
        if(pRoot.left!=null) {
            Mirror(pRoot.left);
        }
        if(pRoot.right!=null) {
            Mirror(pRoot.right);
        }
        return pRoot;

    }
    //层序遍历
    void levelOrderTraversal1(TreeNode root) {
        if(root==null) {
            return;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        //循环
        while (!queue.isEmpty()) {
            TreeNode cur=queue.poll();
            System.out.println(cur.val+" ");
            if(root.left!=null) {
                queue.offer(root.left);
            }
            if(root.right!=null) {
                queue.offer(root.right);
            }
        }
    }
    // 判断一棵树是不是完全二叉树
    boolean isCompleteTree1(TreeNode root) {
        if(root==null) {
            return true;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur=queue.poll();
            if(cur==null) {
                return false;
            }
            int size=queue.size();
            while (size!=0) {
                if(root.left!=null) {
                    queue.offer(root.left);
                }
                if(root.right!=null) {
                    queue.offer(root.right);
                }
                size--;
            }
        }
        return true;
    }
    //非递归二叉树前序遍历
    void preOrderTraversal1(TreeNode root) {
        if(root==null) {
            return;
        }
        TreeNode cur=root;
        Stack<TreeNode> stack=new Stack<>();
        while (cur!=null||!stack.empty()) {
            while (cur!=null) {
                stack.push(cur);
                System.out.print(cur.val+" ");
                cur=cur.left;
            }
            TreeNode top=stack.pop();
            cur=top.right;
        }
    }
    // 中序遍历
    void inOrderTraversal1(TreeNode root) {
        if(root==null) {
            return;
        }
        TreeNode cur=root;
        Stack<TreeNode> stack=new Stack<>();
        while (cur!=null||!stack.empty()) {
            while (cur!=null) {
                stack.push(cur);
                cur=cur.left;
            }
            TreeNode top=stack.pop();
            System.out.print(top.val+" ");
            cur=top.right;
        }

    }
    // 后序遍历
    void postOrderTraversal1(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;//用来指定 上一个被打印的元素

        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == pre) {
                TreeNode popNode = stack.pop();
                System.out.print(popNode.val + " ");
                pre = cur;
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        System.out.println();
    }
    // 遍历思路-求叶子结点个数
    static int leafSize = 0;
    void getLeafSize1(TreeNode root){
        if(root==null) {
            return;
        }
        if(root.left==null&&root.right==null) {
            leafSize++;
        }
        getLeafSize1(root.left);
        getLeafSize1(root.right);
    }
    // 子问题思路-求叶子结点个数
    int getLeafSize2(TreeNode root) {
        if(root==null) {
            return 0;
        }
        if(root.left==null&&root.right==null) {
            return 1;
        }
        return getLeafSize2(root.left)+getLeafSize2(root.right);

    }
    // 获取二叉树的高度
    int getHeight(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int LeftHeight=getHeight(root.left);
        int RightHeight=getHeight(root.right);
        return 1+(LeftHeight>RightHeight?LeftHeight:RightHeight);
    }
    // 查找 val 所在结点，没有找到返回 null
// 按照 根 -> 左子树 -> 右子树的顺序进行查找
// 一旦找到，立即返回，不需要继续在其他位置查找
    TreeNode find1(TreeNode root, char val) {
        if(root==null) {
            return null;
        }
        if(root.val==val) {
            return root;
        }
        TreeNode ret=find1(root.left,val);
        if(ret!=null) {
            return ret;
        }
        ret=find1(root.right,val);
        if(ret!=null) {
            return ret;
        }
        return null;
    }
    //两个相同的树
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p==null&&q==null) {
            return true;
        }
        if(p==null||q==null) {
            return false;
        }
        if(p.val!=q.val) {
            return false;
        }
        return isSameTree1(p.left,q.left)&&isSameTree1(p.right,q.right);
    }
    //判断子树
    public boolean isSubtree1(TreeNode root, TreeNode subRoot) {
        if(root==null||subRoot==null) {
            return false;
        }
        if(isSameTree1(root,subRoot)) {
            return true;
        }
        if(isSubtree1(root.left,subRoot)) {
            return true;
        }
        if(isSubtree1(root.right,subRoot)) {
            return true;
        }
        return false;
    }
    //递增顺序搜索树

    public TreeNode increasingBST(TreeNode root) {
        List<Character> res=new ArrayList<>();
        inorder(root,res);
        TreeNode top=new TreeNode('Q');
        TreeNode cur=top;
        for (char val:res) {
            cur.right=new TreeNode(val);
            cur=cur.right;
        }
        return top.right;
    }
    public void inorder(TreeNode node, List<Character> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }
    //合并两个二叉树
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1==null&&root2==null) {
            return null;
        }
        if(root1==null) {
            return root2;
        }
        if(root2==null) {
            return root1;
        }
        root1.val+=root2.val;
        root1.left=mergeTrees(root1.left,root2.left);
        root1.right=mergeTrees(root1.right,root2.right);
        return root1;
    }
    public static void main(String[] args) {
        Traversal traversal=new Traversal();
        TreeNode root=traversal.createTree();
        traversal.preOrderTraversal(root);
        System.out.println();
        traversal.preOrderTraversal1(root);
        System.out.println();
        traversal.inOrderTraversal(root);
        System.out.println();
        traversal.inOrderTraversal1(root);
        System.out.println();
        traversal.postOrderTraversal(root);
        System.out.println();
        traversal.postOrderTraversal1(root);
        System.out.println();
        System.out.println(traversal.getSize2(root));
        int r=traversal.getLeafSize(root);
        System.out.println(r);
        int a=traversal.getLevelSize(root,4);
        System.out.println(a);
        traversal.levelOrderTraversal(root);
        System.out.println(traversal.levelOrder(root));
        System.out.println(traversal.isCompleteTree(root));

    }
}
