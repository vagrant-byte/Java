import java.util.Stack;

//有效的括号
public class isvalid {
    public static boolean isValid(String s) {
        if(s.length()==1) {
            return false;
        }
        Stack<Character> stack=new Stack<>();
        char[] chars=s.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            if(chars[i]=='('||chars[i]=='{'||chars[i]=='[') {
                stack.push(chars[i]);
            } else if(!stack.isEmpty()){
                if(chars[i]==')'&&stack.peek()!='(') {
                    return false;
                } else if(chars[i]=='}'&&stack.peek()!='{') {
                    return false;
                } else if(chars[i]==']'&&stack.peek()!='[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();

    }
}
