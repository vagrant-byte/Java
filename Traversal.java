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
    // 前序遍历
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

    public static void main(String[] args) {
        Traversal traversal=new Traversal();
        TreeNode root=traversal.createTree();
        traversal.preOrderTraversal(root);
        System.out.println();
        traversal.inOrderTraversal(root);
        System.out.println();
        traversal.postOrderTraversal(root);
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
