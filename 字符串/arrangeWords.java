package 字符串;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

//重新排列句子中的单词
public class arrangeWords {
    public String arrangeWords(String text) {
        if(text.length()==0) {
            return text;
        }
        String[] strings=text.toLowerCase().split(" ");
        Arrays.sort(strings,(a,b)->a.length()-b.length());
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <strings.length-1 ; i++) {
            if(i==0) {
                char ch=strings[0].charAt(0);
                if(ch>='A'&&ch<='Z') {
                    stringBuilder.append(strings[i]+" ");
                } else {
                    stringBuilder.append((char)(ch-32)).append(strings[i].substring(1)+" ");
                }
            }else {
                stringBuilder.append(strings[i]+" ");
            }
        }
        stringBuilder.append(strings[strings.length-1]);
        return stringBuilder.toString();
    }
}
