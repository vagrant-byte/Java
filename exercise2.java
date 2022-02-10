import java.util.ArrayList;
import java.util.List;

public class exercise2 {
    //括号匹配
    List<String> strings=new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        dfs(n,n,"");
        return strings;

    }

    private void dfs(int left, int right, String s) {
        if(left==0&&right==0) {
            strings.add(s);
            return;
        }
        if(left>0) {
            dfs(left-1,right,s+"(");
        }
        if(right>left) {
            dfs(left,right-1,s+")");
        }
    }

    //字符串转化整数
    public static int myAtoi(String s) {
        char[] chars=s.toCharArray();
        int n=s.length();
        int index=0;
        while (index<n&&chars[index]==' ') {
            //去掉前导空格
            index++;
        }
        if(index==n) {
            //前面全部为空格
            return 0;
        }
        boolean flag=false;
        if(chars[index]=='-') {
            flag=true;
            index++;
        }else if(chars[index]=='+') {
            index++;
        }else if(chars[index]<'0'&&chars[index]>'9') {
            return 0;
        }
        long res=0;
        while (index<n&&chars[index]>='0'&&chars[index]<='9') {
            res=res*10+chars[index]-'0';
            index++;
            if(res>Integer.MAX_VALUE) {
                break;
            }
        }
        res=flag?-res:res;
        if(flag&&res<Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if(!flag&&res>Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)res;
    }


    public static void main(String[] args) {
        String s="43";
        int n=myAtoi(s);
        System.out.println(n);
    }
}
