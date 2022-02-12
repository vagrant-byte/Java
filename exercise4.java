import java.util.Arrays;

public class exercise4 {
    //多数元素
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int num=nums[nums.length/2];
        int count=0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]==num) {
                count++;
            }
        }
        if(count>nums.length/2) {
            return num;
        }
        return 0;
    }
    //Excel表列序号
    public static int titleToNumber(String columnTitle) {
        int res=0;
        char[] chars=columnTitle.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            int tmp=chars[i]-'A'+1;
            res=res*26+tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        String s="B";
        int n=titleToNumber(s);
        System.out.println(n);
    }
}
