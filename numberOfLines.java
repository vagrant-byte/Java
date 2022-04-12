import java.util.HashMap;

public class numberOfLines {
    //写字符串需要的行数
    public int[] numberOfLines(int[] widths, String s) {
        char[] chars=s.toCharArray();
        int count=1;
        int sum=0;
        for (int i = 0; i <chars.length; i++) {
            sum+=widths[chars[i]-'a'];
            if(sum>100) {
                count++;
                sum=widths[chars[i]-'a'];
            }
        }
        return new int[]{count,sum};
    }
}
