package 栈和队列;

import java.util.Stack;

public class 用两个栈实现队列 {
    public void CQueue() {

    }
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();
    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if(stack1.isEmpty()&&stack2.isEmpty()) {
            return -1;
        }
        if(stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
