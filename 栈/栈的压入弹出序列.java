package 栈和队列;

import java.util.Stack;

public class 栈的压入弹出序列 {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length!= popped.length) {
            return false;
        }
        Stack<Integer> stack=new Stack<>();
        int i=0;
        for (int j = 0; j <pushed.length ; j++) {
            stack.push(pushed[j]);
            while (i<popped.length&&!stack.isEmpty()&&stack.peek()==popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        int[] push={0,1};
        int[] pop={1,0};
        boolean f=validateStackSequences(push,pop);
        System.out.println(f);
    }
}
