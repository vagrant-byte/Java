import java.util.*;
 class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
  }
public class LeetCode {
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
