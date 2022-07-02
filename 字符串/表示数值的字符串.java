package 字符串;

public class 表示数值的字符串 {
    public boolean isNumber(String s) {
        //去掉前面的空格
        s=s.trim();
        char[] chars=s.toCharArray();
        //小数点
        boolean dotFlag=false;
        //e
        boolean  eFlag=false;
        boolean num=false;
        for (int i = 0; i <chars.length ; i++) {
            if(chars[i]>='0'&&chars[i]<='9') {
                num=true;
            }else if(chars[i]=='.'&&!dotFlag&&!eFlag) {
                dotFlag=true;
            }else if((chars[i]=='e'||chars[i]=='E')&&num&&!eFlag) {
                eFlag=true;
                num=false;
            }else if((chars[i]=='+'||chars[i]=='-')&& (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {

            }else {
                return false;
            }
        }
        return num;
    }
}
