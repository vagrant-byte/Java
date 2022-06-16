import java.util.*;

//数组中的 k-diff 数对
public class findPairs {
    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        List<int[]> list=new ArrayList<>();
        int left=0;
        int right=1;
        int len=nums.length;
        int count=0;
        while (right<len) {
            if(nums[right]-nums[left]<k) {
                right++;
            }else if(nums[right]-nums[left]>k) {
                left++;
                if(left==right) {
                    right++;
                }
            } else {
                int[] tmp=new int[2];
                tmp[0]=nums[left];
                tmp[1]=nums[right];
                if(!contents(list,tmp)) {
                    list.add(tmp);
                    count++;
                }
                left++;
                right++;
            }
        }
        return count;
    }

    private static boolean contents(List<int[]> list, int[] tmp) {
        for (int[] a:list) {
            if(a[0]==tmp[0]&&a[1]==tmp[1]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums={1,1,1,2,2};
        int k=0;
        int n=findPairs(nums,k);
        System.out.println(n);
    }
}
