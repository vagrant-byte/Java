import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class isvalid {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = nums.length - 1;
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (k>j&&nums[j]+nums[k]>-nums[i]) {
                    k--;
                }
                if(j==k) {
                    break;
                }
                if(nums[i]+nums[j]+nums[k]==0){
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[j]);
                    list1.add(nums[k]);
                    res.add(list1);
                }
            }
        }
        return res;
    }

        //盛最多水的容器
    public int maxArea(int[] height) {
        int left=0;
        int right=height.length-1;
        int ans=0;
        while (left<right) {
            int sum=Math.min(height[left],height[right])*(right-left);
            ans=Math.max(ans,sum);
            if(height[left]<height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
    //有效的括号
    public static boolean isValid(String s) {
        if(s.length()==1) {
            return false;
        }
        Stack<Character> stack=new Stack<>();
        char[] chars=s.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            if(chars[i]=='('||chars[i]=='{'||chars[i]=='[') {
                stack.push(chars[i]);
            } else if(!stack.isEmpty()){
                if(chars[i]==')'&&stack.peek()!='(') {
                    return false;
                } else if(chars[i]=='}'&&stack.peek()!='{') {
                    return false;
                } else if(chars[i]==']'&&stack.peek()!='[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();

    }
}
