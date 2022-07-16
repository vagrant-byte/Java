package 字符串;

public class 左旋转字符串 {
    public String reverseLeftWords(String s, int n) {
        StringBuilder s1=new StringBuilder();
        StringBuilder s2=new StringBuilder();
        for (int i = 0; i <n ; i++) {
            s1.append(s.charAt(i));
        }
        for (int i = n; i <s.length() ; i++) {
            s2.append(s.charAt(i));
        }
        return s2.toString()+s1.toString();
    }
}
