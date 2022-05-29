//验证IP地址
public class validIPAddress {
    public static String validIPAddress(String queryIP) {
        return isIPv4(queryIP)?"IPv4":isIPv6(queryIP)?"IPv6":"Neither";
    }

    private static boolean isIPv6(String queryIP) {
        String[] strings=queryIP.split(":",-1);
        if(strings.length!=8) {
            return false;
        }
        for (int i = 0; i < 8 ; i++) {
            if(strings[i].length()==0||strings[i].length()>4) {
                return false;
            }
            for (int j = 0; j < strings[i].length(); j++) {
                if(!(strings[i].charAt(j)>='0'&&strings[i].charAt(j)<='9')&&!(strings[i].charAt(j)>='a'&&strings[i].charAt(j)<='f')&&!(strings[i].charAt(j)>='A'&&strings[i].charAt(j)<='F')) {
                    return false;
                }
            }
        }
        return true;

    }

    private static boolean isIPv4(String queryIP) {
        String[] strings=queryIP.split("\\.",-1);
        if(strings.length!=4) {
            return false;
        }
        for (int i = 0; i <4; i++) {
            if(strings[i].length()==0||strings[i].length()>3||strings[i].length()>1&&strings[i].charAt(0)=='0') {
                return false;
            }
            int sum=0;
            for (int j = 0; j <strings[i].length() ; j++) {
                if(!(strings[i].charAt(j)>='0'&&strings[i].charAt(j)<='9')) {
                    return false;
                }
                sum=sum*10+strings[i].charAt(j)-'0';
                if(sum>255) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s="2001:0db8:85a3:0:0:8A2E:0370:7334:";
        String s1=validIPAddress(s);
        System.out.println(s1);

    }
}
