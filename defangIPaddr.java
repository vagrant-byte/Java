//IP地址无效化
public class defangIPaddr {
    public static String defangIPaddr(String address) {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <address.length() ; i++) {
            if(address.charAt(i)=='.') {
                stringBuilder.append("[.]");
                continue;
            }
            stringBuilder.append(address.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s="1.1.1.1";
        String s1=defangIPaddr(s);
        System.out.println(s1);
    }
}
