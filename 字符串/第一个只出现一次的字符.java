package 字符串;

import java.util.HashSet;
import java.util.Set;

public class 第一个只出现一次的字符 {
    public static char firstUniqChar(String s) {
        int[] tmp=new int[26];
        char[] chars=s.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            tmp[chars[i]-'a']++;
        }
        for (int i = 0; i <chars.length ; i++) {
            if(tmp[chars[i]-'a']==1) {
                return chars[i];
            }
        }
        return ' ';
    }

}
