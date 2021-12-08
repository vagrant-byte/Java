import java.util.*;
 class ListNode {
     //合并排序的数组
     public static void merge(int[] A, int m, int[] B, int n) {
        int k=m+n-1;
        int i=m-1;
        int j=n-1;
        while (i>=0&&j>=0) {
            if(A[i]>B[j]) {
                A[k--]=A[i--];
            } else {
                A[k--]=B[j--];
            }
        }
        while (j>=0) {
            A[k--]=B[j--];
        }
     }

     public static void main(String[] args) {
         int[] A={1,2,3,0,0,0};
         int[] B={2,5,6};
         int m=3;
         int n=3;
         merge(A,m,B,n);
     }
     //跳跃游戏
     public boolean canJump(int[] nums) {
         if(nums.length<=1) {
             return true;
         }
         int cover=0;
         for (int i = 0; i <nums.length ; i++) {
             if(i<=cover) {
                 cover=Math.max(cover,i+nums[i]);
                 if(cover>=nums.length-1) {
                     return true;
                 }
             }

         }
         return false;

     }
     //平衡字符串
     public int balancedStringSplit(String s) {
         int count=0;
         int res=0;
         for (int i = 0; i <s.length() ; i++) {
             if(s.charAt(i)=='R') {
                 res++;
             } else if(s.charAt(i)=='L') {
                 res--;
             }
             if(res==0) {
                 count++;
             }
         }
         return count;

     }
     //买股票的最佳时机
     public int maxProfit(int[] prices) {
         int peace=0;
         for (int i = 1; i <prices.length; i++) {
             if(prices[i]-prices[i-1]>0) {
                 peace+=prices[i]-prices[i-1];
             }
         }
         return peace;
     }
       int val;
       ListNode next;
       ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
public class LeetCode {

     //汉诺塔问题
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(),A,B,C);
    }

    private void move(int size, List<Integer> a, List<Integer> b, List<Integer> c) {
        if(size==1) {
            c.add(a.remove(a.size()-1));
            return;
        } else {
           move(size-1,a,c,b);
           c.add(a.remove(a.size()-1));
           move(size-1,b,a,c);
        }
    }

    //魔术索引
    public static int findMagicIndex(int[] nums) {
        for (int i = 0; i <nums.length ; i++) {
            if (nums[i]==i) {
                return i;
            }
        }
        return -1;
    }

    public static void main9(String[] args) {
        int[] a=new int[]{0,2,3,4,5};
        System.out.println(findMagicIndex(a));
    }
     //截断句子
    public static String truncateSentence(String s, int k) {
        if(k<=0||k>s.length()) {
            return "";
        }
        String[] res=s.split(" ");
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <k-1 ; i++) {
            stringBuilder.append(res[i]+" ");
        }
        stringBuilder.append(res[k-1]);
        return stringBuilder.toString();
    }

    public static void main8(String[] args) {
        String s="hello how are you contest";
        int k=4;
        System.out.println(truncateSentence(s, k));
    }
     //k次取反后最大化的数组和
    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum=0;
        for (int i = 0; i <nums.length && k>0; i++,k--) {
            if(nums[i]>=0) {
                break;
            }
            nums[i]=-nums[i];
        }
        Arrays.sort(nums);
        for (int i = 0; i <nums.length ; i++) {
            sum+=nums[i];
        }
        if(k!=0&&k%2!=0) {
            sum-=2*nums[0];
        }
        return sum;
    }

    public static void main7(String[] args) {
        int[] a={4,3,2};
        System.out.println(largestSumAfterKNegations(a, 1));
    }
     //赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length()>magazine.length()) {
            return false;
        }
        int[] nums=new int[26];
        for (int i = 0; i <magazine.length() ; i++) {
            nums[magazine.charAt(i)-'a']++;
        }
        for (int i = 0; i <ransomNote.length() ; i++) {
            nums[ransomNote.charAt(i)-'a']--;
            if(nums[ransomNote.charAt(i)-'a']<0) {
                return false;
            }
        }
        return true;

    }
     //相对名次
    public static String[] findRelativeRanks(int[] score) {
        String[] place=new String[]{"Cold Medal","Silver Medal","Bronze Medal"};
        String[] res=new String[score.length];
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) ->{
            return score[o2] - score[o1];
        });
        for (int i = 0; i <score.length ; i++) {
            queue.add(i);
        }
        for(int i = 0; i < score.length; i++){
            int x = queue.poll();
            if(i < 3){
                res[x] = place[i];
            }
            else{
                res[x] = Integer.toString(i+1);
            }
        }
        return res;
    }

    public static void main6(String[] args) {
        int[] a={5,4,3,2,1};
        findRelativeRanks(a);
    }
     //回文链表
    public boolean isPalindrome(ListNode head) {
        ListNode node1=head;
        ListNode node2=head;
        Stack<Integer> stack=new Stack<>();
        while (node1!=null) {
            stack.push(node1.val);
            node1=node1.next;
        }
        while (node2!=null) {
            if(node2.val==stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
            node2=node2.next;
        }
        return true;

    }
     //用栈实现队列
//     Stack<Integer> stack1=new Stack<>();
//     Stack<Integer> stack2=new Stack<>();
//    public void push(int x) {
//        stack1.push(x);
//    }
//
//    /** Removes the element from in front of queue and returns that element. */
//    public int pop() {
//        if(stack1.isEmpty()&&stack2.isEmpty()) {
//            return -1;
//        } else if(stack2.isEmpty()) {
//            while (!stack1.isEmpty()) {
//                int tmp=stack1.peek();
//                stack1.pop();
//                stack2.push(tmp);
//            }
//        }
//        int tmp=stack2.peek();
//        stack2.pop();
//        return tmp;
//    }
//
//    /** Get the front element. */
//    public int peek() {
//        if(stack1.isEmpty()&&stack2.isEmpty()) {
//            return -1;
//        } else if(stack2.isEmpty()) {
//            while (!stack1.isEmpty()) {
//                int tmp=stack1.peek();
//                stack1.pop();
//                stack2.push(tmp);
//            }
//        }
//        return stack2.peek();
//
//    }
//
//    /** Returns whether the queue is empty. */
//    public boolean empty() {
//        return stack1.isEmpty()&&stack2.isEmpty();
//
//    }
    //连续字符
    public static int maxPower(String s) {
        int max=1;
        int count=1;
        for (int i = 1; i <s.length() ; i++) {
            if(s.charAt(i)==s.charAt(i-1)) {
                count++;
            } else {
                max=Math.max(max,count);
                count=1;
            }
        }
        return Math.max(max,count);
    }

    public static void main5(String[] args) {
        String s="abbcccddddeeeeedcba";
        System.out.println(maxPower(s));
    }
     //链表相交
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA=headA;
        ListNode curB=headB;
        int lengthA=0;
        int lengthB=0;
        while (curA!=null) {
            lengthA++;
            curA=curA.next;
        }
        while (curB!=null) {
            lengthB++;
            curB=curB.next;
        }
        curA=headA;
        curB=headB;
        int len=lengthA-lengthB;
        if(lengthA<lengthB) {
            curA=headB;
            curB=headA;
            len=lengthB-lengthA;
        }
        while (len!=0) {
            curA=curA.next;
            len--;
        }
        while (curA!=null&&curB!=null&&curA!=curB) {
            curA=curA.next;
            curB=curB.next;
        }
        return curA;
    }
     //第K个最小的素数分数
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        List<int[]> res=new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            for (int j = i+1; j <arr.length ; j++) {
                res.add(new int[]{arr[i],arr[j]});
            }
        }
        //自定义排序
        Collections.sort(res,(x,y) ->x[0]*y[1]-y[0]*x[1]);
        return res.get(k-1);

    }

    public static void main4(String[] args) {
        int[] arr={1,2,3,5};
        int k=3;
        int[] a=kthSmallestPrimeFraction(arr, k);
        for (int i = 0; i <a.length ; i++) {
            System.out.println(a[i]);
        }
    }
     //分割链表
    public ListNode partition(ListNode head, int x) {
        //分割链表
       ListNode node1=new ListNode();
       ListNode node2=new ListNode();
       ListNode head1=node1;
       ListNode head2=node2;
       ListNode cur=head;
       while (cur!=null) {
           if(cur.val<x) {
               node1.next=cur;
               node1=node1.next;
           } else {
               node2.next=cur;
               node2=node2.next;
           }
           cur=cur.next;
       }
       node1.next=head2.next;
       node2.next=null;
       return head1.next;

    }

     //找出字符串中所有字母异位词
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list=new ArrayList<>();
        if(s==null||p==null) {
            return list;
        }
        if(s.length()<p.length()) {
            return list;
        }
        int[] mark=new int[26];
        for (int i = 0; i <p.length() ; i++) {
            mark[p.charAt(i)-'a']++;
        }

        for (int i = 0; i <=s.length()-p.length() ; i++) {
            int[] temp=mark.clone();
            for (int j = i; j <i+p.length() ; j++) {
                if(temp[s.charAt(j)-'a']>0) {
                    temp[s.charAt(j)-'a']--;
                } else {
                    break;
                }
            }
            boolean flag=true;
            for (int j = 0; j <26 ; j++) {
                if(temp[j]!=0) {
                    flag=false;
                    break;
                }
            }
            if(flag) {
                list.add(i);
            }
        }
        return list;
    }
    public static void main3(String[] args) {
        String s="cbaebabacd";
        String p="abc";
        System.out.println(findAnagrams(s, p));

    }
     //删除中间节点
    public void deleteNode1(ListNode node) {
        if(node==null) {
            return;
        }
        node.val=node.next.val;
        node.next=node.next.next;
    }
     //倒数第k个节点
    public int kthToLast(ListNode head, int k) {
        if(head==null) {
            return -1;
        }
        ListNode first=head;
        ListNode slow=head;
        while (k!=0) {
            first=first.next;
            k--;
        }
        while (first!=null) {
            first=first.next;
            slow=slow.next;
        }
        return slow.val;

    }
     //移除重复节点
    public  static ListNode removeDuplicateNodes(ListNode head) {
        if(head==null) {
            return null;
        }
        Set<Integer> set=new HashSet<>();
        ListNode newHead=head;
        set.add(head.val);
        while (head.next!=null) {
            if (set.contains(head.next.val)) {
                head.next= head.next.next;
            } else {
                set.add(head.next.val);
                head = head.next;
            }

        }
        return newHead;

    }
    public boolean isFlipedString1(String s1, String s2) {
        if(s1.length()!=s2.length()) {
            return false;
        }
        String ss=s2+s2;
        return ss.contains(s1);
    }
     //字符串轮转
    public static boolean isFlipedString(String s1, String s2) {
        int length1=s1.length();
        int length2=s2.length();
        if(length1!=length2) {
            return false;
        }
        if(s1==null||s2==null) {
            return false;
        }
        int i=0;
        while (s1.charAt(i)!=s2.charAt(0)) {
            i++;
        }
        //System.out.println(i);
        StringBuilder stringBuilder=new StringBuilder();
        for (int j = i; j <length1 ; j++) {
            stringBuilder.append(s1.charAt(j));
        }
        for (int j = 0; j <i ; j++) {
            stringBuilder.append(s1.charAt(j));
        }
        //System.out.println(stringBuilder.toString());
        if(s2.equals(stringBuilder.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static void main2(String[] args) {
        String s1="";
        String s2="";
        System.out.println(isFlipedString(s1, s2));
    }


     //字符串压缩
    public String compressString(String S) {
        if(S==null||S.length()==0) {
            return S;
        }
        StringBuilder stringBuilder=new StringBuilder();
        char prev=S.charAt(0);
        int times=1;
        for (int i = 1; i <S.length() ; i++) {
            if(S.charAt(i)==prev) {
                times++;
            } else {
                stringBuilder.append(prev).append(times);
                prev=S.charAt(i);
                times=1;
            }
        }
        stringBuilder.append(prev).append(times);
        return S.length()>stringBuilder.toString().length()?stringBuilder.toString():S;


    }
     //一次编辑
     public boolean oneEditAway(String first, String second) {
         int[][] dp=new int[first.length()+1][second.length()+1];
         for (int i = 1; i <=first.length() ; i++) {
             dp[i][0]=i;
         }
         for (int i = 1; i <=second.length() ; i++) {
             dp[0][i]=i;
         }
         for (int i = 1; i <=first.length() ; i++) {
             for (int j = 1; j <=second.length() ; j++) {
                 if(first.charAt(i-1)==second.charAt(j-1)) {
                     dp[i][j]=dp[i-1][j-1];
                 } else {
                     dp[i][j]=Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1]+1);
                 }
             }
         }
         return dp[first.length()][second.length()]<=1;

     }
     //最长和谐子序列
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int begin=0;
        int end=0;
        int res=0;
        for (end=0;end<nums.length;end++) {
            while (nums[end]-nums[begin]>1) {
                begin++;
            }
            if(nums[end]-nums[begin]==1) {
                 res=Math.max(res,end-begin+1);
            }
        }
        return res;


    }
     //回文结构
    public boolean canPermutePalindrome(String s) {
        char[] chars=s.toCharArray();
        Set<Character> set=new HashSet<>();
        for (int i = 0; i <s.length() ; i++) {
            if(!set.add(chars[i])) {
                set.remove(chars[i]);
            }
        }
        return set.size()<=1;

    }
     //灯泡开关
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);

    }
     //URL化
    public String replaceSpaces(String S, int length) {
        StringBuilder stringBuilder=new StringBuilder();
        char[] chars=S.toCharArray();
        for (int i = 0; i <length ; i++) {
            if(chars[i]==' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(chars[i]);
            }
        }
        return stringBuilder.toString();

    }
     //是否互为字符串重排
    public boolean CheckPermutation(String s1, String s2) {
        if(s1.length()!=s2.length()) {
            return false;
        }
        char[] chars1=s1.toCharArray();
        char[] chars2=s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i <chars1.length ; i++) {
            if(chars1[i]!=chars2[i]) {
                return false;
            }
        }
        return true;

    }
     //检测大写字母
    public boolean detectCapitalUse(String word) {
        int upper=0;//大写字母
        int lower=0;//小写字母
        for (int i = 0; i <word.length() ; i++) {
            if(word.charAt(i)>='a') {
                lower++;
            } else {
                upper++;
            }
        }
        if(lower==word.length()) {
            return true;
        }
        if(upper==word.length()) {
            return true;
        }
        if(upper==1&&word.charAt(0)<'a') {
            return true;
        }
        return false;


    }
     //提莫攻击
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int sum=0;
        for(int i=1;i<timeSeries.length;i++) {
            if(timeSeries[i]-timeSeries[i-1]>=duration) {
                sum=sum+duration;
            } else {
                sum+=timeSeries[i]-timeSeries[i-1];
            }
        }
        return sum+duration;

    }
     //丢失的数字
    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <nums.length ; i++) {
            if(i!=nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main1(String[] args) {
        int[] nums={0,1};
        System.out.println(missingNumber(nums));
    }
     //最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if(strs==null) {
            return "";
        }
        if(strs.length==1) {
            return strs[0];
        }
        String str=strs[0];
        for (int i = 0; i <strs.length ; i++) {
            while(strs[i].indexOf(str)!=0) {
                str=str.substring(0,str.length()-1);
            }
        }
        return str;

    }
     //两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur=new ListNode(-1);
        ListNode prev=cur;
        int tmp=0;
        while (l1!=null||l2!=null||tmp!=0) {
            if(l1!=null) {
                tmp+=l1.val;
                l1=l1.next;
            }
            if(l2!=null) {
                tmp+=l2.val;
                l2=l2.next;
            }
            prev.next=new ListNode(tmp%10);
            prev=prev.next;
            tmp/=10;
        }
        return cur.next;
    }
     //猜数字游戏
    public String getHint(String secret, String guess) {
        int a=0;
        int b=0;
        int[] arr=new int[10];
        for(int i=0;i<secret.length();i++) {
            if(secret.charAt(i)==guess.charAt(i)) {
                a++;
            } else {
                if(arr[secret.charAt(i)-'0']++<0) {
                    b++;
                }
                if(arr[guess.charAt(i)-'0']-->0) {
                    b++;
                }
            }
        }
        return a+"A"+b+"B";

    }
     //范围求和
    public int maxCount(int m, int n, int[][] ops) {
        for (int i = 0; i <ops.length ; i++) {
            m=m>ops[i][0]?ops[i][0]:m;
            n=n>ops[i][1]?ops[i][1]:n;
        }
        return m*n;

    }
     //有效平方数
    public boolean isPerfectSquare(int num) {
        int low=0;
        int high=num;
        while(low<=high) {
            int mid=low+(high-low)/2;
            long square=(long)mid*mid;
            if(square==num) {
                return true;
            } else if(square<num) {
                low=mid+1;
            } else{
                high=mid-1;
            }
        }
        return false;
    }
    
     //删除链表的节点
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
    //分糖果
    public int distributeCandies(int[] candyType) {
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i <candyType.length ; i++) {
            set.add(candyType[i]);
        }
        return Math.min(set.size(),candyType.length/2);

    }
    //键盘行
    public String[] findWords(String[] words) {
        String s1 = "qwertyuiopQWERTYUIOP";
        String s2 = "asdfghjklASDFGHJKL";
        String s3 = "zxcvbnmZXCVBNM";
        List<String> list = new ArrayList<>();
        for (int i = 0; i <words.length ; i++) {
            int n1=0;
            int n2=0;
            int n3=0;
            int len=words[i].length();
            for (int j = 0; j <len ; j++) {
                if(s1.contains(words[i].charAt(j)+"")) {
                    n1++;
                } else if(s2.contains(words[i].charAt(j)+"")) {
                    n2++;
                } else {
                    n3++;
                }
            }
            if(n1==len||n2==len||n3==len) {
                list.add(words[i]);
            }
        }
        return list.toArray(new String[list.size()]);

    }
    //只出现一次的数字
    public int[] singleNumber(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int[] result=new int[2];
        for (int i = 0; i <nums.length ; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i],1);
            } else {
                int tmp=map.get(nums[i]);
                map.put(nums[i],tmp+1);
            }
        }
        int i=0;
        for (int n:nums) {
            if(map.get(n)==1) {
                result[i++]=n;
            }
        }
        return result;


    }
    //重新排序得到2的幂
    public boolean reorderedPowerOf2(int n) {
        String s=countDigits(n);
        for(int i=1;i<=1e9;i<<=1){
            if(countDigits(i).equals(s)){
                return true;
            }
        }
        return false;
    }
    public String countDigits(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            ++cnt[n % 10];
            n /= 10;
        }
        return new String(cnt);
    }
}
