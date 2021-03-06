public class BinarySearchTree {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int val) {
            this.val=val;
        }
    }
    //查找
    public Node search(Node root,int key) {
        Node cur=root;
        while (cur!=null) {
            if(cur.val==key) {
                return cur;
            } else if(cur.val<key) {
                cur=cur.right;
            } else {
                cur=cur.left;
            }
        }
        return null;
    }
    //插入
    public boolean insert(Node root,int key) {
        Node node=new Node(key);
        if(root==null) {
            root=node;
            return true;
        }
        Node cur=root;
        Node parent=null;
        while (cur!=null) {
            if(cur.val==key) {
                return false;
            } else if(cur.val<key) {
                parent=cur;
                cur=cur.right;
            } else {
                parent=cur;
                cur=cur.left;
            }
        }
        if(parent.val>key) {
            parent.left=node;
        } else {
            parent.right=node;
        }
        return true;
    }
    //删除
    public void remove(Node root,Node cur,Node parent) {
        if(cur.left==null) {
            if(cur==root) {
                root=cur.right;
            } else if(cur==parent.left) {
                parent.left=cur.right;
            } else {
                parent.right=cur.right;
            }
        } else if(cur.right==null) {
            if(cur==root) {
                root=cur.left;
            } else if(cur==parent.left) {
                parent.left=cur.left;
            } else {
                parent.right=cur.left;
            }
        } else {
            Node tp=cur;
            Node t=cur.right;
            while (t.left!=null) {
                tp=t;
                t=t.left;
            }
            if(tp.right==t) {
                tp.right=t.right;
            } else {
                tp.left=t.right;
            }
        }
    }
    public void removeKey(Node root ,int key) {
        if(root==null) {
            return;
        }
        Node cur=root;
        Node parent=null;
        while (cur!=null) {
            if(cur.val==key) {
                remove(root,cur,parent);
                return;
            } else if(cur.val<key) {
                parent=cur;
                cur=cur.right;
            } else {
                parent=cur;
                cur=cur.left;
            }
        }
    }
}
