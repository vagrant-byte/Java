import java.util.ArrayList;
import java.util.List;

public class morris {
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
    }
    public void morris(TreeNode head) {
        if(head==null) {
            return;
        }
        TreeNode cur=head;
        TreeNode mostRight=null;
        while (cur!=null) {
            mostRight=cur.left;
            if(mostRight!=null) {
                while (mostRight.right!=null&&mostRight.right!=cur) {
                    //找到左子树的最右节点
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null) {
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else {
                    mostRight.right=null;
                }
            }
            cur=cur.right;
        }
    }

    //前序遍历
    public List<Integer> morrisPrv(TreeNode head) {
        List<Integer> list=new ArrayList<>();
        if(head==null) {
            return list;
        }
        TreeNode cur=head;
        TreeNode mostRight=null;
        while (cur!=null) {
            mostRight=cur.left;
            if(mostRight!=null) {
                while (mostRight.right!=null&&mostRight.right!=cur) {
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null) {
                    list.add(cur.val);
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else {
                    mostRight.right=null;
                }
            }else {
                list.add(cur.val);
            }
            cur=cur.right;
        }
        return list;
    }

    //中序遍历
    public List<Integer> morrisIno(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if(root==null) {
            return list;
        }
        TreeNode cur=root;
        TreeNode mostRight=null;
        while (cur!=null) {
            mostRight=cur.left;
            if(mostRight!=null) {
                while (mostRight.right!=null&&mostRight.right!=cur) {
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null) {
                    mostRight.right=cur;
                    cur=cur.left;
                    continue;
                }else {
                    mostRight.right=null;
                }
            }
            list.add(cur.val);
            cur=cur.right;
        }
        return list;
    }
}
