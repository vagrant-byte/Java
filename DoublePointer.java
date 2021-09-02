package 剑指offer;
//双指针
public class DoublePointer {
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
}
