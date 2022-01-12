import java.util.Scanner;
import java.util.Stack;

public class demo1 {
    //逆波兰表达式求值
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for (String s:tokens) {
            if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")) {
                int n1=stack.pop();
                int n2=stack.pop();
                if(s.equals("+")) {
                    stack.push(n2+n1);
                }else if(s.equals("-")) {
                   stack.push(n2-n1);
                } else if(s.equals("*")) {
                   stack.push(n2*n1);
                } else {
                    stack.push(n2/n1);
                }
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String[] tokens=new String[]{"2","1","+","3","*"};
        System.out.println(evalRPN(tokens));
    }
    //括号的最大嵌套深度
    public static int maxDepth(String s) {
        if(s==null||s.indexOf("(")==-1||s.indexOf(")")==-1) {
            return 0;
        }
        int depth=0;
        Stack<Character> stack=new Stack<>();
        char[] chars=s.toCharArray();
        for(int i=0;i<chars.length;i++) {
            if(chars[i]=='(') {
                stack.push('(');
                if(stack.size()>depth) {
                    depth=stack.size();
                }
            } else if(chars[i]==')') {
                stack.pop();
            }
        }
        return depth;
    }

    public static void main1(String[] args) {
        String s="1";
        System.out.println(maxDepth(s));
    }
}
