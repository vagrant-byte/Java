package 栈;

import java.util.Stack;

//最小栈
public class MinStack {
    public Stack<Integer> stack;
    public Stack<Integer> min_stack;
    public MinStack() {
        stack=new Stack<>();
        min_stack=new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(min_stack.isEmpty()||min_stack.peek()>val) {
            min_stack.push(val);
        } else {
            min_stack.push(min_stack.peek());
        }
    }

    public void pop() {
        stack.pop();
        min_stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}
