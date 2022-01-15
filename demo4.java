import java.util.LinkedList;
import java.util.Queue;

public class demo4 {

    //滑动窗口的平均值
    public int size;
    public Queue<Integer> queue;
    public int sum;
    public demo4(int size) {
        this.size=size;
        queue=new LinkedList<>();
    }

    public double next(int val) {
        if(queue.size()>=size) {
            sum-=queue.poll();
        }
        queue.add(val);
        sum+=val;
        return sum/queue.size();
    }
    //对称二叉树
//    public boolean isSymmetric(TreeNode root) {
//        if(root==null) {
//            return true;
//        }
//        return isMirror(root.left,root.right);
//    }
//    public boolean isMirror(TreeNode t1,TreeNode t2) {
//        if(t1==null&&t2==null) {
//            return true;
//        }
//        if(t1==null||t2==null) {
//            return false;
//        }
//        if(t1.val!=t2.val) {
//            return false;
//        }
//        return isMirror(t1.left,t2.right)&&isMirror(t1.right,t2.left);
//    }
}
