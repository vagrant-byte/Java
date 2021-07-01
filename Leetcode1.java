import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
//数组中超过一半的数字
class Solution14 {
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
}
//旋转数组最小数
class Solution13 {
    //二分查找
    public int minArray(int[] numbers) {
        int low=0;
        int high=numbers.length-1;
        while (low<high) {
            int mid=low+(high-low)/2;
            if(numbers[mid]>numbers[high]) {
                low=mid+1;
            }else if(numbers[mid]<numbers[high]) {
                high=mid;
            } else {
                high-=1;
            }
        }
        return numbers[low];
    }
        /*for (int i = 0; i <numbers.length-1 ; i++) {
            for (int j = 0; j <numbers.length-1-i ; j++) {
                if(numbers[j]>numbers[j+1]) {
                    int tmp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=tmp;
                }
            }
        }
        return numbers[0];
    }*/

}
//替换空格
class Solution12 {
    public String replaceSpace(String s) {
        StringBuffer s1=new StringBuffer();
        char ch[]=s.toCharArray();
        for (int i = 0; i <s.length() ; i++) {
            if(ch[i]!=' ') {
                s1.append(ch[i]);
            } else {
                s1.append("%20");
            }
        }
        return s1.toString();
    }
}
//第一次只出现一次的字符
class Solution11 {
    public char firstUniqChar(String s) {
        char ch[]=s.toCharArray();
        int res[]=new int[26];
        for (int i = 0; i <s.length() ; i++) {
            res[ch[i]-'a']++;
        }
        for(int i=0;i<s.length();i++) {
            if(res[ch[i]-'a']==1) {
                return ch[i];
            }
        }
        return ' ';

    }
}
//缺失的数字
class Solution10 {
    public int missingNumber(int[] nums) {
        int num=nums.length;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]!=i) {
                return i;
            }
        }
        return num;
    }
}
//反转字符串
class Solution9 {
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
}
//重复数字
class Solution8 {
    public int findRepeatNumber(int[] nums) {
  //创建一个新数组存放数据
        int[] arr=new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            //这个数字多次出现就++
            int num=arr[nums[i]]++;
            if(num>1) {
                return nums[i];
            }
        }
        return -1;
    }
}
//最小的k个数
class Solution7 {
    public int[] getLeastNumbers(int[] arr, int k) {
        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = 0; j<arr.length-1-i  ; j++) {
                if(arr[j]>arr[j+1]) {
                    int tmp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
        int[] a=new int[k];
        for (int i = 0; i <k ; i++) {
            a[i]=arr[i];
        }
        return a;
    }
}
//两个栈实现队列
class CQueue {
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();
    public CQueue() {

    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack1.empty() && stack2.empty()) {
            return -1;
        }
        if(stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();

    }
}
//调整数组顺序使奇数位于偶数前面
//while for 循环   if 判断
class Solution6 {
    public int[] exchange(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while (left<right) {
            while (left<right&&nums[left]%2!=0) {
                left++;
            }
            while (left<right&&nums[right]%2==0) {
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
}
//速算机器人
class Solution5 {
    public int calculate(String s) {
        int x=1;
        int y=0;
        for (int i = 0; i <s.length() ; i++) {
            char ch=s.charAt(i);
            if(ch=='A') {
                x=x*2+y;
            } else {
                y=x+2*y;
            }
        }
        return x+y;
    }
}
//青蛙跳台阶
class Solution4 {
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
}
//模拟实现strStr
class Solution3 {
    public int strStr(String haystack, String needle) {
        int m=haystack.length();
        int n=needle.length();
        for (int i = 0; i <=m-n ; i++) {
            if(needle.equals(haystack.substring(i,i+n))) {
                return i;
            }
        }
        return -1;
    }
}
//回文数
class Solution2 {
    public boolean isPalindrome(int x) {
        if(x<0) {
            return false;
        }
        int tmp=0;
        int num=x;//防止交换后X值改变
        while(num!=0) {
            tmp=tmp*10+num%10;
            num=num/10;
        }
        if(tmp==x) {
            return true;
        } else {
            return false;
        }
    }
}
//整数反转
class Solution1 {
    public int reverse(int x) {
        int num=0;
        while(x!=0) {
            int tmp=num;//存放之间的结果
            num=num*10+x%10;
            x=x/10;
            //判读是否栈溢出
            if(num/10!=tmp) {
                return 0;
            }
        }
        return num;
    }
}
//找最大值
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int i=0;
        for(i=0;i<arr.length;i++) {
            if(arr[i]>arr[i+1]) {
                break;
            }
        }
        return i;
    }
}
public class Leetcode1 {
    //删除有序数组重复项
    public int removeDuplicates(int[] nums) {
        int slow=0;
        int fast=1;
        while (fast<nums.length){
            if(nums[slow]!=nums[fast]) {
                nums[++slow]=nums[fast];
            }
            fast++;
        }
        return slow+1;

    }
    //二进制中一的个数
    public class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count=0;
            while(n!=0) {
                count++;
                n=n&(n-1);
            }
            return count;
        }
    }
    //冒泡排序
    public static void main(String[] args) {

        int[] arr={3,2,5,7,2,1};
        for (int i = 0; i <arr.length-1; i++) {
            for (int j = 0; j<arr.length-1-i ; j++) {
                if(arr[j]>arr[j+1]) {
                    int tmp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }
}
