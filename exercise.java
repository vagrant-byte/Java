package 剑指offer;

import java.util.*;

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
    //连续子数组最大和
    public int maxSubArray(int[] nums) {
        int sum=0;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <nums.length ; i++) {
            if(sum<=0) {
                sum=nums[i];
            } else {
                sum+=nums[i];
            }
            max=Math.max(sum,max);
        }
        return max;
    }
    //礼物的最大价值
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int []dp = new int[n];
        for(int i = 0; i < m; i++){
            dp[0] = dp[0] + grid[i][0];
            for(int j = 1; j < n; j++){
                dp[j] = Math.max(dp[j], dp[j-1]) + grid[i][j];
            }
        }
        return dp[n-1];
    }
    //把数字翻译成字符串
    public int translateNum(int num) {
        if(num<=9) {
            return 1;
        }
        int ba=num%100;
        if(ba<=9||ba>=26) {
            return translateNum(num/10);
        } else {
            return translateNum(num/10)+translateNum(num/100);
        }

    }
    //最长不含重复字符的子字符串
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0||s.length()==1) {
            return s.length();
        }
        int start=0;
        int maxLength=0;
        char[] ch=s.toCharArray();
        for(int i=1;i<s.length();i++) {
            for(int j=start;j<i;j++) {
                if(ch[j]==ch[i]) {
                    start=j+1;
                    break;
                }
            }
            maxLength=Math.max(maxLength,i-start+1);
        }
        return maxLength;
    }
    //调整数组顺序是奇数在前偶数在后
    public int[] exchange(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while (left<right) {
            while(left<right&&nums[left]%2!=0) {
                left++;
            }
            while(left<right&&nums[right]%2==0) {
                right--;
            }
            if(left<right) {
                int tmp=nums[left];
                nums[left]=nums[right];
                nums[right]=tmp;
                left++;
                right--;
            }

        }
        return nums;
    }
    //和为S的两个数字
    public int[] twoSum(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while (left<right) {
            int cur=nums[left]+nums[right];
            if(cur==target) {
                return new int[] {nums[left],nums[right]};
            } else if(cur<target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }
    //反转单词顺序
    public String reverseWords(String s) {
        s=s.trim();//删除头尾空格
        String[] ss=s.split(" ");//空格分隔
        StringBuilder a=new StringBuilder();
        for(int i=ss.length-1;i>=0;i--) {//反向添加
            if(!ss[i].equals("")) {
                a.append(ss[i].trim());
                a.append(" ");
            }
        }
        return a.toString().trim();
    }
    //把数组排成最小数
    public String minNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }
    //扑克牌中的顺子 如果0的个数大于不连续数之间的距离则为顺子否则不是
    public boolean isStraight(int[] nums) {
        //首先进行排序
        Arrays.sort(nums);
        int zero=0;
        int diff=0;
        for (int i = 0; i <nums.length-1 ; i++) {
            if(nums[i]==0) {
                zero++;
            } else {
                if(nums[i]==nums[i+1]) return false;
                if (nums[i] + 1 != nums[i+1]) {
                    diff+=nums[i+1]-nums[i]-1;//之间只差n-1个元素
                }
            }
        }
        return zero>=diff;
    }
    //1+2+3+...+N的和
    public int sumNums(int n) {
        return (int)(Math.pow(n,2)+n)>>1;
    }
    //数值的整数次方
    public double myPow(double x, int n) {
        if(n==-1) {
            return 1/x;
        }
        if(n==0) {
            return 1;
        }
        if(n==1) {
            return x;
        }
        double half=myPow(x,n/2);
        double mod=myPow(x,n%2);
        return half*half*mod;
    }
    //位运算
    //二进制中1的个数
    public int hammingWeight(int n) {
        int count=0;
        while(n!=0) {
            count++;
            n=n&(n-1);
        }
        return count;
    }
    //不用加减乘除完成加法
    public int add(int a, int b) {
        //a^b是不考虑进位的加法
        //a&b<<1是进位
        return b==0 ? a :add(a^b,(a&b)<<1);
    }
    //数组中数字出现的次数 所有数字都出现2次只有两个数字出现一次
    public int[] singleNumbers(int[] nums) {
        int sum=0;
        for (int i = 0; i <nums.length ; i++) {
            sum^=nums[i];
        }
        //得到sum二进制的1的最低位
        int flag=(-sum)&sum;
        int result[]=new int[2];
        for(int i=0;i<nums.length;i++) {
            if((flag&nums[i])==0) {
                result[0]^=nums[i];
            } else {
                result[1]^=nums[i];
            }

        }
        return result;
    }
    //所有数字都出现3次只有一个数字出现1次
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int a = map.getOrDefault(nums[i], 0);
            map.put(nums[i], a + 1);
        }
        for (Integer a : map.keySet()) {
            if(map.get(a) == 1) return a;
        }
        return -1;
    }
    //数组中出现次数超过一半的数字
    public int majorityElement(int[] nums) {
        int count=0;
        int card=0;//出现次数最多的数字
        for (int i = 0; i <nums.length ; i++) {
            if(count==0) {
                card=nums[i];
            }
            if(card==nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return card;
    }
    //构建乘积数组
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] B = new int[n];
        for (int i = 0, product = 1; i < n; product *= a[i], i++)       /* 从左往右累乘 */
            B[i] = product;
        for (int i = n - 1, product = 1; i >= 0; product *= a[i], i--)  /* 从右往左累乘 */
            B[i] *= product;
        return B;
    }
    //返回圆圈里的最后一个数
    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }
    //顺时针打印矩阵
    public int[] spiralOrder(int[][] matrix) {
        int row=matrix.length;
        if(row==0) {
            return new int[0];
        }
        int col=matrix[0].length;
        int[] res=new int[row*col];
        int left=0;//左
        int right=col-1;//右
        int top=0;//上
        int bottom=row-1;//下
        int index=0;//记录数组位置
        while (true) {
            //从左往右
            for (int i = left; i <=right ; i++) {
                res[index++]=matrix[top][i];
            }
            if(++top>bottom) {
                break;
            }
            //从上往下
            for (int i = top; i <=bottom ; i++) {
                res[index++]=matrix[i][right];
            }
            if(--right<left) {
                break;
            }
            //从右往左
            for (int i = right; i >=left ; i--) {
                res[index++]=matrix[bottom][i];
            }
            if(--bottom<top) {
                break;
            }
            //从下往上走
            for (int i = bottom; i >=top ; i--) {
                res[index++]=matrix[i][left];
            }
            if(++left>right) {
                break;
            }

        }
        return res;
    }
    //栈的压入弹出
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque();
        int j = 0;
        for (int elem : pushed) {
            stack.push(elem);
            while (j < popped.length && !stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == popped.length;
    }
    //字符串转整数
    public int strToInt(String str) {
        //去空格
        str=str.trim();
        if(str.length()==0) {
            return 0;
        }
        //isMinus标记是不是负数
        boolean isMinus=false;
        char[] ch=str.toCharArray();
        if(ch[0]=='+'||ch[0]=='-'||Character.isDigit(ch[0])) {
            if(ch[0]=='+'||ch[0]=='-') {
                if(ch[0]=='-') {
                    isMinus=true;
                }
                //删除首位
                ch=Arrays.copyOfRange(ch,1,ch.length);
            }
            int index=0;
            //结果可能超出范围用long
            long res=0;
            while(index<ch.length&&Character.isDigit(ch[index])) {
                res*=10;
                res+=ch[index]-'0';
                if(res > Integer.MAX_VALUE){
                    //正负号看是正数负数，返回最大值
                    return isMinus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                //别忘了往后走一位
                index++;
            }
            //long转int
            return isMinus ? -(int)res : (int)res;
        }
        //首位不对
        return 0;
    }
}
