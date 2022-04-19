import java.util.ArrayList;
import java.util.List;

public class shortestToChar {
    //字符的最短距离
    public static int[] shortestToChar(String s, char c) {
        int len=s.length();
        int[] answer = new int[len];
        char[] chars=s.toCharArray();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <len ; i++) {
            if(chars[i]==c) {
                list.add(i);
                answer[i]=0;
            } else {
                answer[i]=-1;
            }
        }
        int[] tmp=new int[list.size()];
        for (int i = 0; i <tmp.length ; i++) {
            tmp[i]=list.get(i);
        }
        for (int i = 0; i <len ; i++) {
            int min=Integer.MAX_VALUE;
            for (int j = 0; j <tmp.length ; j++) {
                if(answer[i]==0) {
                    break;
                } else {
                    if(min>Math.abs(tmp[j]-i)) {
                        min=Math.abs(tmp[j]-i);
                    }
                    answer[i]=min;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String s="aaab";
        char c='b';
        int[] r=shortestToChar(s,c);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
    }
}
