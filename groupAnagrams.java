import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//字母异位词分组
public class groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
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
}
