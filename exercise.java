package 剑指offer;

import java.util.Stack;
//复杂链表的复制
/*class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
    public Node copyRandomList(Node head) {
        if(head==null) {
            return null;
        }
        Node cur=head;
        //链表节点复制
        while(cur!=null) {
            Node copyNode=new Node(cur.val);
            copyNode.next=cur.next;
            cur.next=copyNode;
            cur=cur.next.next;
        }
        //从头开始遍历链表，通过 cur.next.random = cur.random.next 可以将复制节点的随机指针串起来，当然需要判断 cur.random 是否存在
        cur=head;
        while(cur!=null) {
            if(cur.random!=null) {
                cur.next.random=cur.random.next;
            }
            cur=cur.next.next;
        }
        //将链表一分为2
        Node copyHead=head.next;
        cur = head;
        Node curCopy = head.next;
        while (cur != null) {
            cur.next = cur.next.next;
            cur = cur.next;
            if (curCopy.next != null) {
                curCopy.next = curCopy.next.next;
                curCopy = curCopy.next;
            }
        }
        return copyHead;
    }*/
public class exercise {
    Stack<Integer> stack=new Stack<>();
    Stack<Integer> minStack=new Stack<>();
    public void MinStack() {

    }

    public void push(int x) {
        stack.push(x);
        if(minStack.empty()) {
            minStack.push(x);
        } else {
            int tmp=minStack.peek();
            if(x>tmp) {
                minStack.push(tmp);
            } else {
                minStack.push(x);
            }
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
    //替换空格
    public String replaceSpace(String s) {
        StringBuffer stringBuffer=new StringBuffer();
        char[] chars=s.toCharArray();
        for (int i = 0; i <s.length() ; i++) {
            if(chars[i]!=' ') {
                stringBuffer.append(chars[i]);
            } else {
                stringBuffer.append("%20");
            }
        }
        return stringBuffer.toString();
    }
    //左旋转字符串
    public static void swap(char[] ch,int left,int right) {
        while (left<right) {
            char tmp=ch[left];
            ch[left]=ch[right];
            ch[right]=tmp;
            left++;
            right--;
        }

    }
    public String reverseLeftWords(String s, int n) {
        /*StringBuffer stringBuffer=new StringBuffer();
        char[] chars=s.toCharArray();
        for (int i = n; i <s.length() ; i++) {
            stringBuffer.append(chars[i]);
        }
        for (int i = 0; i <n ; i++) {
            stringBuffer.append(chars[i]);
        }
        return stringBuffer.toString();*/
        char[] chars=s.toCharArray();
        swap(chars,0,n-1);
        swap(chars,n,s.length()-1);
        swap(chars,0,s.length()-1);
        return new String(chars);
    }
    //二维数组中查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0) {
            return false;
        }
        int i=matrix.length;
        int j=matrix[0].length;
        int row=0;
        int col=j-1;
        while (row<i&&col>=0) {
            if(matrix[row][col]>target) {
                col--;
            } else if(matrix[row][col]<target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
    //旋转数组的最小数
    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++)
            if (numbers[i] < numbers[i - 1])
                return numbers[i];
        return numbers[0];
    }
    //第一次只出现一次的字符
    public char firstUniqChar(String s) {
        int[] count = new int[256];
        char[] chars = s.toCharArray();
        for(char c : chars)
            count[c]++;
        for(char c : chars){
            if(count[c] == 1)
                return c;
        }
        return ' ';
    }
    //斐波那契数列 青蛙跳台阶
    public int numWays(int n) {
        if(n<2) {
            return 1;
        }
        int [] num=new int[n+1];
        num[0]=1;
        num[1]=1;
        for(int i=2;i<=n;i++) {
            num[i]=(num[i-1]+num[i-2])%1000000007;
        }
        return num[n];
    }
    //股票最大利润
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1) {
            return 0;
        }
        int res=0;
        int min=prices[0];
        for (int i = 1; i <prices.length ; i++) {
            if(prices[i]<min) {
                min=prices[i];
            } else {
                res=Math.max(res,prices[i]-min);
            }
        }
        return res;
    }
}
