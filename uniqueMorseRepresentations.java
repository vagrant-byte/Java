import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//唯一摩尔斯密码词
public class uniqueMorseRepresentations {
    public static int uniqueMorseRepresentations(String[] words) {
//        String[] table = {
//                ".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."
//        };
//        Set<String> set = new HashSet<>();
//        for(String word : words){
//            StringBuilder sb = new StringBuilder();
//            for(char c : word.toCharArray()){
//                sb.append(table[c-'a']);
//            }
//            set.add(sb.toString());
//        }
//        return set.size();
        HashMap<Character,String> map=new HashMap<>();
        map.put('a',".-");
        map.put('b',"-...");
        map.put('c',"-.-.");
        map.put('d',"-..");
        map.put('e',".");
        map.put('f',"..-.");
        map.put('g',"--.");
        map.put('h',"....");
        map.put('i',"..");
        map.put('j',".---");
        map.put('k',"-.-");
        map.put('l',".-..");
        map.put('m',"--");
        map.put('n',"-.");
        map.put('o',"---");
        map.put('p',".--.");
        map.put('q',"--.-");
        map.put('r',".-.");
        map.put('s',"...");
        map.put('t',"-");
        map.put('u',"..-");
        map.put('v',"...-");
        map.put('w',".--");
        map.put('x',"-..-");
        map.put('y',"-.--");
        map.put('z',"--..");
        if(words.length==0||words==null) {
            return 0;
        }
        if (words.length==1) {
            return 1;
        }
        Set<String> set=new HashSet<>();
        for (int i = 0; i <words.length ; i++) {
            char[] chars=words[i].toCharArray();
            StringBuffer stringBuffer=new StringBuffer();
            for (int j = 0; j <chars.length ; j++) {
                stringBuffer.append(chars[j]);
            }
            set.add(stringBuffer.toString());
        }
        return set.size();
    }

    public static void main(String[] args) {
        String[] w={"gin","zen","gig","msg"};
        System.out.println(uniqueMorseRepresentations(w));
    }
}
