import sun.font.DelegatingShape;

import java.util.HashMap;
import java.util.Map;

public class exercise {
    //罗马数字转整数
    public static int romanToInt(String s) {
        Map<Character,Integer> map=new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int num=0;
        char[] chars=s.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            if(i+1<chars.length&&map.get(chars[i])<map.get(chars[i+1])) {
                num+=map.get(chars[i+1])-map.get(chars[i]);
                i++;
            } else {
                num+=map.get(chars[i]);
            }
        }
        return num;
    }
    //最长公共前缀
    public static String longestCommonPrefix(String[] strs) {
        if(strs==null) {
            return "";
        }
        if(strs.length==1) {
            return strs[0];
        }
        String str=strs[0];
        for (int i = 1; i <strs.length ; i++) {
            str=longestCommon(str,strs[i]);
            if(str.length()==0) {
                break;
            }
        }
        return str;
    }

    private static String longestCommon(String str, String str1) {
        int min=Math.min(str.length(),str1.length());
        int index=0;
        while (index<min&&str.charAt(index)==str1.charAt(index)) {
            index++;
        }
        return str.substring(0,index);
    }

    public static void main(String[] args) {
        String[] strings=new String[]{"flower","flow","flight"};
        String s=longestCommonPrefix(strings);
        System.out.println(s);
    }
}
