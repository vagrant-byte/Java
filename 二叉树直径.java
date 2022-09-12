package 树型DP;

public class 二叉树直径 {
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
            private int maxDistance;//距离
            private int height;//高度
            public Info(int dis,int hei) {
                this.maxDistance=dis;
                this.height=hei;
            }
        }
        public Info process(TreeNode x) {
            if(x==null) {
                return new Info(0,0);
            }
            Info leftInfo=process(x.left);
            Info rightInfo=process(x.right);
            int p1=leftInfo.maxDistance;
            int p2=rightInfo.maxDistance;
            int p3=leftInfo.height+1+rightInfo.height;
            //最大距离=左子树的最大距离与右子树的最大距离与左子树高度+1+右子树最大高度的最大值
            int distance=Math.max(Math.max(p1,p2),p3);
            int height=Math.max(leftInfo.height,rightInfo.height)+1;
            return new Info(distance,height);
        }
        public int diameterOfBinaryTree(TreeNode root) {
            Info res=process(root);
            return res.maxDistance;
        }

    }
