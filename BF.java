package 在字符串中找字串;

public class BF {
    public static int Bf(String str,String sub) {
        if(str==null||sub==null) {
            return -1;
        }
        int lenStr=str.length();
        int lenSub=sub.length();
        if(lenStr==0||lenSub==0) {
            return -1;
        }
        int i=0;
        int j=0;
        while (i<lenStr&&j<lenSub) {
            if(str.charAt(i)==sub.charAt(j)) {
                i++;
                j++;
            } else {
                i=i-j+1;
                j=0;
            }
        }
        if(j>=lenSub) {
            return i-j;
        } else {
            return -1;
        }
    }
    public static void main(String[] args) {
        String str="abababc";
        String sub="abcd";
        System.out.println(Bf(str, sub));
    }
}
