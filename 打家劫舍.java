package 树型DP;

public class 打家劫舍 {
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
  public class Info{
        private int laiMax;//要
        private int buMax;//不要
        public Info(int lai,int bu) {
            this.laiMax=lai;
            this.buMax=bu;
        }
  }
  public Info process(TreeNode x) {
        if(x.left==null&&x.right==null) {
            //没有子树，要当前值
            return new Info(x.val,0);
        }
        int lai=x.val;
        int bu=0;
        if(x.left!=null) {
            Info nextInfo=process(x.left);//表示下一个
            lai+=nextInfo.buMax;//上一个值那么下一个不能要
            bu+=Math.max(nextInfo.laiMax,nextInfo.buMax);//当上一个不要，那么下一个选要或不要的最大值
        }
        if(x.right!=null) {
            Info nextInfo=process(x.right);
            lai+=nextInfo.buMax;
            bu+=Math.max(nextInfo.laiMax,nextInfo.buMax);
        }
        return new Info(lai,bu);
  }
    public int rob(TreeNode root) {
        Info res=process(root);
        return Math.max(res.laiMax,res.buMax);
    }
}
