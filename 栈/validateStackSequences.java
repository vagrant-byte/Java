package 栈;

import java.util.Stack;

//栈的压入，弹出序列
public class validateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed==null||popped==null) {
            return false;
        }
        Stack<Integer> stack=new Stack<>();
        int j=0;
        for (int i = 0; i <pushed.length ; i++) {
            stack.push(pushed[i]);
            while (j<popped.length&&!stack.isEmpty()&&stack.peek()==popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
