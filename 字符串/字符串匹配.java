package 字符串;

public class 字符串匹配 {
    public int match_str_in_sentence (String s, String x) {
        // write code here
        if(s.equals("")||x.equals("")) {
            return -1;
        }
        String[] strings=s.split(" ");
        char[] chars=x.toCharArray();
        int size=x.length();
        for (int i = 0; i <strings.length ; i++) {
            char[] chars1=strings[i].toCharArray();
            boolean flag=true;
            if(chars1.length<size) {
                flag=false;
            }else {
                for (int j = 0; j <size ; j++) {
                    if(chars[j]!=chars1[j]) {
                        flag=false;
                        break;
                    }
                }
            }
            if(flag) {
                return i+1;
            }
        }
        return -1;
    }
}
