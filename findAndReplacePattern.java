import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//查找和替换模型
public class findAndReplacePattern {
    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> list=new ArrayList<>();
        for (int i = 0; i <words.length ; i++) {
            if(wordPattern(pattern,words[i])) {
                list.add(words[i]);
            }
        }
        return list;
    }
    public static boolean wordPattern(String pattern, String s) {
        if(pattern==null||s==null) {
            return false;
        }
        if(pattern.length()!=s.length()) {
            return false;
        }
        HashMap<Character,Character> map=new HashMap<>();
        for (int i = 0; i <pattern.length() ; i++) {
            char tmp=pattern.charAt(i);
            //key存在
            if(map.containsKey(tmp)) {
                //不对应
                if(!map.get(tmp).equals(s.charAt(i))) {
                    return false;
                }
            } else {
                //key不存在
                if(map.containsValue(s.charAt(i))) {
                    return false;
                }else {
                    map.put(tmp,s.charAt(i));
                }
            }
        }
        return true;
    }
//    //单词规律
//    public static boolean wordPattern(String pattern, String s) {
//        if(pattern==null||s==null) {
//            return false;
//        }
//        String[] strings=s.split(" ");
//        if(pattern.length()!=strings.length) {
//            return false;
//        }
//        HashMap<Character,String> map=new HashMap<>();
//        for (int i = 0; i <pattern.length() ; i++) {
//            char tmp=pattern.charAt(i);
//            //key存在
//            if(map.containsKey(tmp)) {
//                //不对应
//                if(!map.get(tmp).equals(strings[i])) {
//                    return false;
//                }
//            } else {
//                //key不存在
//                if(map.containsValue(strings[i])) {
//                    return false;
//                }else {
//                    map.put(tmp,strings[i]);
//                }
//            }
//        }
//        return true;
//    }

    public static void main(String[] args) {
        String[] words={"abc","dep","mee","aqq","ccc"};
        String pattern="abb";
        findAndReplacePattern(words,pattern);
    }
}
