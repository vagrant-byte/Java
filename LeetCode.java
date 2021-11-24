import java.util.*;
 class ListNode {
       int val;
       ListNode next;
       ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
public class LeetCode {
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

    public static void main(String[] args) {

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
