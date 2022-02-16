import java.util.ArrayList;
import java.util.List;

public class exercise7 {
    //分割回文串
    public List<List<String>> partition(String s) {
        List<List<String>> res=new ArrayList<>();
        dfs(s,0,new ArrayList<String>(),res);
        return res;
    }

    private void dfs(String s, int start, ArrayList<String> strings, List<List<String>> res) {
        if(start==s.length()) {
            res.add(new ArrayList<>(strings));
            return;
        }
        for (int i = start; i <s.length() ; i++) {
            String s1=s.substring(start,i+1);
            if(!isPalidrome(s1)) {
                continue;
            }
            strings.add(s1);
            dfs(s,i+1,strings,res);
            strings.remove(strings.size()-1);
        }
    }

    private boolean isPalidrome(String s1) {
        int left=0;
        int right=s1.length()-1;
        while (left<=right) {
            if(s1.charAt(left)==s1.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    //在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==1&&nums[0]==target) {
            return new int[]{0,0};
        }
        int[] res={-1,-1};
        int left=0;
        int right=nums.length-1;
        for (int i = 0; i <nums.length; i++) {
            if(nums[i]==target) {
                res[0]=i;
                break;
            }
        }
        for (int i = nums.length-1; i >res[0] ; i--) {
            if(nums[i]==target) {
                res[1]=i;
                break;
            }
        }
        if(res[1]==-1&&res[0]!=-1) {
            res[1]=res[0];
        }
        return res;
    }
}
