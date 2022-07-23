package 栈和队列;

import java.util.Stack;

public class 有效括号序列 {
    public static boolean isValid (String s) {
        // write code here
        Stack<Character> stack=new Stack<>();
        char[] chars=s.toCharArray();
        if(chars[0]==')'||chars[0]=='}'||chars[0]==']') {
            return false;
        }
        for (int i = 0; i <chars.length ; i++) {
            if(chars[i]=='('||chars[i]=='['||chars[i]=='{') {
                stack.push(chars[i]);
            }else {
                if(!stack.isEmpty()) {
                    char tmp=stack.pop();
                    if(!((chars[i]==')'&&tmp=='(')||
                            (chars[i]==']'&&tmp=='[')||
                            (chars[i]=='}'&&tmp=='{'))) {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s="(){}[]";
        boolean f=isValid(s);
        System.out.println(f);
    }
}
