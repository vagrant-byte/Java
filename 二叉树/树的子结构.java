package 二叉树;

public class 树的子结构 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null||B==null) {
            return false;
        }
        boolean flag=false;
        if(A.val==B.val) {
            flag=isChild(A,B);
        }
        if(!flag) {
            flag=isSubStructure(A.left,B);
        }
        if(!flag) {
            flag=isSubStructure(A.right,B);
        }
        return flag;
    }

    private boolean isChild(TreeNode begin, TreeNode end) {
        if(end==null) {
            return true;
        }
        if(begin==null) {
            return false;
        }
        if(begin.val!=end.val) {
            return false;
        }
        return isChild(begin.left,end.left)&&isChild(begin.right,end.right);
    }
}
