public class sumRootToLeaf {
    class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
        //从根到叶的二进制数之和
        public int sumRootToLeaf(TreeNode root) {
             return helper(root,0);
        }

        private int helper(TreeNode root, int sum) {
             if(root==null) {
                 return 0;
             }
             sum=2*sum+root.val;
             if(root.left==null&&root.right==null) {
                 return sum;
             }
             return helper(root.left,sum)+helper(root.right,sum);
        }

}
