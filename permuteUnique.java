package 回溯;

import java.util.ArrayList;
import java.util.List;
//全排列
public class permuteUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        if(nums==null) {
            return list;
        }
        permuteUniqueHelper(nums,0,list);
        return list;
    }

    private void permuteUniqueHelper(int[] nums, int start, List<List<Integer>> list) {
        if(start==nums.length-1) {
            //去重
            List<Integer> list1=new ArrayList<>();
            for (int i = 0; i <nums.length ; i++) {
                list1.add(nums[i]);
            }
            if(!isExist(list,list1)) {
                list.add(list1);
            }
            return;
        }
        for (int i = start; i <nums.length ; i++) {
            swap(nums,start,i);
            permuteUniqueHelper(nums,start+1,list);
            swap(nums,start,i);
        }
    }

    private boolean isExist(List<List<Integer>> list, List<Integer> list1) {
        return list.contains(list1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
