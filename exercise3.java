import com.sun.applet2.AppletParameters;

import java.util.*;

public class exercise3 {
    //子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i <nums.length ; i++) {
            int size=res.size();
            for (int j = 0; j <size ; j++) {
                List<Integer> list1=new ArrayList<>(res.get(j));
                list1.add(nums[i]);
                res.add(list1);
            }
        }
        return res;
    }

    //字母异位词分组
    public static  List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map=new HashMap<>();
        for (int i = 0; i <strs.length ; i++) {
            char[] chars=strs[i].toCharArray();
            Arrays.sort(chars);
            String key=String.valueOf(chars);
            if(!map.containsKey(key)) {
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strings={"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists=groupAnagrams(strings);
        for (List<String> list:lists) {
            System.out.println(list);
        }
    }
}
