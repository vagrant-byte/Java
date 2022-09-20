package 动态规划;

import java.util.ArrayList;
import java.util.List;

public class 括号生成 {
    List<String> res=new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        String s="";
        process(s,0,0,n);
        return res;
    }

    private void process(String s, int i, int j, int n) {
        if(i==n&&j==n) {
            res.add(s);
            return;
        }
        if(i>n||j>n||j>i) {
            return;
        }
        process(s+"(",i+1,j,n);
        process(s+")",i,j+1,n);

    }
}
