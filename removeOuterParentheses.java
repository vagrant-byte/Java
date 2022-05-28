import java.util.Stack;

public class removeOuterParentheses {
    //删除最外层的括号
    public static String removeOuterParentheses(String s) {
        int left=0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' && left++ > 0)
                res.append('(');
            if (s.charAt(i) == ')' && --left > 0)
                res.append(')');
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s="(()())(())";
        String a=removeOuterParentheses(s);
        System.out.println(s);
    }
}
