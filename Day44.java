import java.util.Scanner;

public class Day44 {
    //反转字符
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String res="";
        String[] cur=s.split(" ");
        for (int i = 0; i <cur.length ; i++) {
            char[] chars=cur[i].toCharArray();
            for (int j = 0; j <chars.length ; j++) {
                if((chars[j]>='a'&&chars[j]<='z')||(chars[j]>='A'&&chars[j]<='Z')||(chars[j]==' ')) {
                    res+=chars[j];
                } else {
                    res+=" ";
                }
            }
            res+=" ";
        }
        String[] s1=res.split(" ");
        String res1="";
        for (int i = s1.length-1; i >=0 ; i--) {
            res1+=s1[i]+" ";
        }
        System.out.println(res1);
    }
    //骆驼命名法
    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            String s=sc.next();
            String[] strings=s.split("_");
            String res=strings[0];
            for (int i = 1; i <strings.length ; i++) {
                char[] chars=strings[i].toCharArray();
                for (int j = 0; j <chars.length ; j++) {
                    chars[0]-=32;
                    res+=chars[j];
                }
            }
            System.out.println(res);
        }
    }
}
