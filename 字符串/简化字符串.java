package 字符串;

import javafx.scene.transform.Scale;

import java.util.Scanner;

//例如aaabcc，我们简化为a3bc2
public class 简化字符串 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            String s=sc.nextLine();
            int k=sc.nextInt();
            int[] tmp=new int[26];
            char[] chars=s.toCharArray();
            for (int i = 0; i <chars.length ; i++) {
                tmp[chars[i]-'a']++;
            }
            StringBuilder stringBuilder=new StringBuilder();
            for (int i = 0; i <tmp.length ; i++) {
                if(tmp[i]==1) {
                    stringBuilder.append((char)('a'+i));
                }
                if(tmp[i]>1) {
                    stringBuilder.append((char)('a'+i));
                    stringBuilder.append(tmp[i]);
                }
            }
            String s1=stringBuilder.toString();
            System.out.println(s1);
            System.out.println(s1.length()-k);
        }
    }
}
