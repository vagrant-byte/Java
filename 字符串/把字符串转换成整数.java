package 字符串;

public class 把字符串转换成整数 {
    public int strToInt(String str) {
        long res=0;
        boolean negative=false;
        int i=0;
        if(str==null||str.length()==0) {
            return 0;
        }
        while(i<str.length()&&str.charAt(i)==' ') {
            i++;
        }
        if(i==str.length()) {
            return 0;
        }
        if(!Character.isDigit(str.charAt(i))&&str.charAt(i)!='+'&&str.charAt(i)!='-')//开头若不为数字或正负号返回
            return 0;
        if(str.charAt(i)=='-'){negative=true; i++;}//判断正负
        else if(str.charAt(i)=='+') i++;
        while(i<str.length()&&Character.isDigit(str.charAt(i)))//累加
        {
            res=res*10+str.charAt(i)-'0';
            if(negative==false&&res>Integer.MAX_VALUE)//判断是否越界
                return Integer.MAX_VALUE;
            else if(negative==true&&-res<Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            i++;
        }
        if(negative) {
            return (int)-res;
        }else{
            return (int)res;
        }
    }
//    public static int strToInt(String str) {
//    //去掉开头的空格
//        String tmp=str.trim();
//        if(str==null||tmp==null) {
//            //字符串为空或者字符串仅包含空白字符
//            return 0;
//        }
//        StringBuilder stringBuilder=new StringBuilder();
//        char[] chars=tmp.toCharArray();
//        String res="";
//        if(chars[0]=='+'||chars[0]=='-'||(chars[0]>='0'&&chars[0]<='9')) {
//            stringBuilder.append(chars[0]);
//            for (int i = 1; i <chars.length ; i++) {
//                if ((chars[i] >= '0' && chars[i] <= '9')) {
//                    stringBuilder.append(chars[i]);
//                } else {
//                    res = stringBuilder.toString();
//                    char symbol = res.charAt(0);
//                    if (symbol == '-') {
//                        int num = Integer.parseInt(res.substring(1));
//                        if (num * (-1) <= Integer.MIN_VALUE) {
//                            return Integer.MIN_VALUE;
//                        }
//                        return num * (-1);
//                    } else if (symbol == '+') {
//                        int num = Integer.parseInt(res.substring(1));
//                        if (num >= Integer.MAX_VALUE) {
//                            return Integer.MAX_VALUE;
//                        }
//                        return num;
//                    } else {
//                        int num = Integer.parseInt(res);
//                        return num >= Integer.MAX_VALUE ? Integer.MAX_VALUE : num;
//                    }
//                }
//            }
//        }
//        String s=stringBuilder.toString();
//        if(s==null||s.length()==0) {
//            return 0;
//        }else if(s.charAt(0)=='-') {
//            int num=Integer.parseInt(s.substring(1));
//            return -num<=Integer.MIN_VALUE?Integer.MIN_VALUE:-num; }else if(s.charAt(0)=='+') {
//            int num=Integer.parseInt(s.substring(1));
//            return num>=Integer.MAX_VALUE?Integer.MAX_VALUE:num;
//        }else {
//            return Integer.parseInt(s)>=Integer.MAX_VALUE?Integer.MAX_VALUE:Integer.parseInt(s);
//        }
//    }

    public static void main(String[] args) {
        String s="4192  woth   wordas";
       // int r=strToInt(s);
        //System.out.println(r);
    }
}
