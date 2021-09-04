package 剑指offer;

import java.util.HashMap;
import java.util.HashSet;

//查找算法
public class lookupAlgorithm {
    //字符串中重复的数字
    public int findRepeatNumber(int[] nums) {
       /* HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i <nums.length ; i++) {
            if(set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;*/
        int[] arr=new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            arr[nums[i]]++;
            if(arr[nums[i]]>1) {
                return nums[i];
            }
        }
        return -1;
    }
    //在排序数组中查找数字
    public int search(int[] nums, int target) {
        //二分查找
        int left=0;
        int right=nums.length-1;
        int count=0;
        while (left<right) {
            int mid=(right+left)/2;
            if(nums[mid]<target) {
                left=mid+1;
            }
            if(nums[mid]>=target) {
                right=mid;
            }
        }
        while (left<nums.length&&nums[left++]==target) {
            count++;
        }
        return count;
    }
    //0-n-1中缺失的数字
    public int missingNumber(int[] nums) {
        int num=nums.length;
        for (int i = 0; i <nums.length; i++) {
            if(i!=nums[i]) {
                return i;
            }
        }
        return num;
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
    //旋转数组的最小数
    public int minNumberInRotateArray(int [] array) {
        if(array.length==0||array==null) {
            return 0;
        }
        //中间值查找 数组分为两部分两部分都有序 如果中间值大于left下标值证明left到mid有序最小的在右半部分反之在左半部分
        int left=0;
        int right=array.length-1;
        int mid=0;
        while (array[left]>=array[right]) {
            //左右相邻最小的为最右边因为数组有序
            if(right-left==1) {
                mid=right;
                break;
            }
            mid=(left+right)/2;
            //当三个数都相等时线性遍历
            if(array[left]==array[mid]&&array[mid]==array[right]) {
                int result=array[left];
                for (int i = left+1; i <right ; i++) {
                    if(result>array[i]) {
                        result=array[i];
                    }
                }
                return result;
            }

            if(array[mid]>=array[left]) {
                left=mid;
            } else {
                right=mid;
            }
        }
        return array[mid];

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
    //奇数在前偶数在后顺序不变
    public void reOrderArray(int [] array) {
        if(array==null||array.length==0) {
            return;
        }
        //保持顺序不变采用直接插入的方法 找到奇数将其保存后，将其前面的数字向后移插入奇数
        int k=0;//记录奇数插入位置
        for (int i = 0; i <array.length ; i++) {
            //表示奇数
            if((array[i]&1)==1) {
                int tmp=array[i];
                int j=i;
                //将要插入位置到找到奇数位置间的元素都往后移
                while (j>k) {
                    array[j]=array[j-1];
                    j--;
                }
                array[k]=tmp;
                k++;
            }
        }
    }
    //数组中出现次数一半以上的数字
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array==null||array.length==0) {
            return 0;
        }
        int number=array[0];
        int times=1;
        for (int i = 1; i <array.length ; i++) {
            if(times==0) {
                number=array[i];
                times=1;
            }else if(array[i]==number) {
                times++;
            } else {
                times--;
            }
        }
        int count=0;
        for (int i = 0; i <array.length ; i++) {
            if(array[i]==number) {
                count++;
            }
        }
        if(count>=array.length/2) {
            return number;
        }
        return 0;
    }
    //替换空格
    public String replaceSpace(StringBuffer str) {
        int count=0;//用来记录空格个数
        for (int i = 0; i <str.length() ; i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }
        int new_length=str.length()+2*count;
        int old_end=str.length()-1;
        int new_end=new_length-1;
        str.setLength(new_length);
        while (old_end>=0&&new_end>=0) {
            if(str.charAt(old_end)==' ') {
                str.setCharAt(new_end--,'0');
                str.setCharAt(new_end--,'2');
                str.setCharAt(new_end--,'%');
                old_end--;
            } else {
                str.setCharAt(new_end--,str.charAt(old_end));
                old_end--;

            }
        }
        return str.toString();
    }
}
