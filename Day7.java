import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Day7 {
    //合法括号判断
    public boolean chkParenthesis(String A, int n) {
        if(A.length()==0||A.length()!=n) {
            return false;
        }
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i <n ; i++) {
            if(A.charAt(i)=='(') {
                stack.push(A.charAt(i));
            } else if(A.charAt(i)==')') {
                if(stack.empty()) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                return false;
            }
        }
        return stack.empty();
    }
    //斐波那契数列
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int N=scanner.nextInt();
        if(N<=3) {
            System.out.println(0);
            return;
        }
        int f1=0;
        int f2=1;
        int f3=1;
        while (f3<N) {
            f3=f1+f2;
            f1=f2;
            f2=f3;
        }
        int res1=f3-N;
        int res2=N-f1;
        if(res1>res2) {
            System.out.println(res2);
            return;
        } else {
            System.out.println(res1);
            return;
        }
    }
}
