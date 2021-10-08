package 每日一题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {
    //重复的DNA序列
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list=new ArrayList<>();
        Map<String,Integer> map=new HashMap<>();
        for (int i = 0; i +10<=s.length() ; i++) {
            String s1=s.substring(i,i+10);
            int cnt=map.getOrDefault(s1,0);
            if(cnt==1) {
                list.add(s1);
            }
            map.put(s1,cnt+1);
        }
        return list;

    }
}
