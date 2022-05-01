import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
  class TreeNode {
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
public class getAllElements {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list=new ArrayList<>();
        leftCon(root1,list);
        leftCon(root2,list);
        Collections.sort(list);
        return list;
    }

    private void leftCon(TreeNode root1, List<Integer> list) {
        if(root1!=null) {
            if(root1.left!=null) {
                leftCon(root1.left,list);
            }
            list.add(root1.val);
            if(root1.right!=null) {
                leftCon(root1.right,list);
            }
        }
    }
}
