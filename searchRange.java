//在排序数组中查找元素的第一个和最后一个位置
public class searchRange {
    public static int[] searchRange(int[] nums, int target) {
        int len=nums.length;
        int[] res={-1,-1};
        for (int i = 0; i <len ; i++) {
            if(nums[i]==target) {
                res[0]=i;
                break;
            }
        }
        for (int i = len-1; i >=0 ; i--) {
            if(nums[i]==target) {
                res[1]=i;
                break;
            }
        }
        for (int i = 0; i <res.length ; i++) {
            if(res[i]==0) {
                res[i]=-1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums={1};
        int[] res=new int[2];
        System.out.println(res[0]);
    }
}
