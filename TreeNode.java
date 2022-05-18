package 二叉树;

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
}
