import java.util.Arrays;
//高度检查器
public class heightChecker {
    public int heightChecker(int[] heights) {
        int len=heights.length;
        int[] expected=new int[len];
        for (int i = 0; i <len ; i++) {
            expected[i]=heights[i];
        }
        Arrays.sort(expected);
        int count=0;
        for (int i = 0; i <len ; i++) {
            if(heights[i]!=expected[i]) {
                count++;
            }
        }
        return count;
    }
}
