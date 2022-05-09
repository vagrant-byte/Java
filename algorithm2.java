package 字符串;

public class algorithm2 {
    //1.替换空格
    public static String replaceSpace(String s) {
        int count=0;//空格个数
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)==' ') {
                count++;
            }
        }
        int len=s.length()+2*count;//替换后字符串的长度
        char[] chars=new char[len+1];
        int i=len-1;//新字符串末尾
        int j=s.length()-1;//原字符串末尾
        while (i>=0&&j>=0) {
            if(s.charAt(j)==' ') {
                chars[i--]='0';
                chars[i--]='2';
                chars[i--]='%';
                j--;
            } else {
                chars[i--]=s.charAt(j--);
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String s="we are happy";
        String s1=replaceSpace(s);
        System.out.println(s1);
    }
}
