public class demo5 {
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
  //叶子相似的树
  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
      String s1=copy(root1,"");
      String s2=copy(root2,"");
      return s1.equals(s2);
  }

    private String copy(TreeNode root1, String s1) {
        TreeNode cur=root1;
        if(cur==null) {
            return s1;
        }
        if(cur.right==null&&cur.left==null) {
            s1+='-';
            s1+=cur.val;
            return s1;
        }
        return copy(cur.left,s1)+(copy(cur.right,s1));
    }
//    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
//        StringBuilder s1=new StringBuilder();
//        StringBuilder s2=new StringBuilder();
//        return s1.equals(s2);
//    }
//
//    private StringBuilder copy(TreeNode root1, StringBuilder s1) {
//        TreeNode cur=root1;
//        if(cur==null) {
//            return s1;
//        }
//        if(cur.right==null&&cur.left==null) {
//            s1.append(cur.val);
//        }
//        return copy(cur.left,s1).append(copy(cur.right,s1));
//    }


}
