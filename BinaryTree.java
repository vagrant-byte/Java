package 二叉树;

import javax.swing.tree.TreeNode;
import java.util.*;

public class BinaryTree {
    int val;
    BinaryTree.TreeNode left;
    BinaryTree.TreeNode right;
    BinaryTree(int x) { val = x; }
    //从上到下打印二叉树
        public int[] levelOrder(TreeNode root) {
            Queue<TreeNode> que = new LinkedList<>();
            if (root != null) {
                que.add(root);
            }
            int[] temp = new int[1010];
            int count = 0;
            while(!que.isEmpty()) {
                TreeNode cur = que.poll();
                temp[count++] = cur.val;
                if (cur.left != null)
                    que.add(cur.left);
                if (cur.right != null)
                    que.add(cur.right);
            }
            int[] result = new int[count];
            for (int i = 0; i < count; i++) {
                result[i] = temp[i];
            }
            return result;

        }
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        // 使用队列先进先出的特性
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            // 先将根节点加入队列
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            ArrayList<Integer> tmp = new ArrayList<>();
            // 每一层遍历一遍
            // size会随着赋值初始化一遍,即使过程中queue的size变化得等到此次循环结束后重新赋值
            for (int size = queue.size(); size > 0; size--) {
                // 获取每个子TreeNode
                TreeNode node = queue.poll();
                // 记录每层打印结果
                tmp.add(node.val);
                // 获取子节点
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            lists.add(tmp);
        }
        return lists;
    }
    //树的子树
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null||B==null) return false;
        return dfs(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B);

    }
    public boolean dfs(TreeNode A,TreeNode B) {
        if(B==null) return true;
        if(A==null) return false;
        return A.val==B.val&&dfs(A.left,B.left)&&dfs(A.right,B.right);
    }
    //二叉树镜像
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null) return null;
        TreeNode tmpNode=root.left;
        root.left=mirrorTree(root.right);
        root.right=mirrorTree(tmpNode);
        return root;

    }
    //对称二叉树
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null) {
            //只有根节点
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
    public boolean isSymmetric(TreeNode root) {
        if(root==null) {
            return true;
        }
        return isMirror(root.left,root.right);

    }
    //二叉搜素树第K大节点
    public int kthLargest(TreeNode root, int k) {
        // clarification:  root == null?   k <= 1?
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list.get(list.size() - k);
    }
    //二叉树深度
    public int maxDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        if(root.left==null&&root.right==null) {
            return 1;
        }
        int depthleft=maxDepth(root.left);
        int depthright=maxDepth(root.right);
        return (depthleft>depthright?depthleft:depthright)+1;
    }
    //平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root==null) {
            return true;
        }
        if(root.left==null&&root.right==null) {
            return true;
        }
        int leftdepth=maxDepth(root.left);
        int rightdepth=maxDepth(root.right);
        if(leftdepth-rightdepth>1||rightdepth-leftdepth>1) {
            return false;
        }
        return isBalanced(root.left)&&isBalanced(root.right);
    }
    //最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) {
            return root;
        }
        if(root==p||root==q) {
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if (left != null && right != null) {
            // p q 一个在左，一个在右
            return root;
        }
        if (left != null) {
            // p q 都在左子树
            return left;
        }
        if (right != null) {
            // p q 都在右子树
            return right;
        }
        return null;
    }
    //重建二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        if (n == 0)
            return null;
        int rootVal = preorder[0], rootIndex = 0;
        for (int i = 0; i < n; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1 + rootIndex), Arrays.copyOfRange(inorder, 0, rootIndex));
        root.right = buildTree(Arrays.copyOfRange(preorder, 1 + rootIndex, n), Arrays.copyOfRange(inorder, rootIndex + 1, n));

        return root;


    }

    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) return;
        if (root.left != null) helper(root.left, list);
        list.add(root.val);
        if (root.right != null) helper(root.right, list);
    }

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
}

