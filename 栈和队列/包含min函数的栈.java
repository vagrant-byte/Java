package 栈和队列;

import java.util.Stack;

public class 包含min函数的栈 {
    Stack<Integer> stack=new Stack<>();
    Stack<Integer> minStack=new Stack<>();
    public void push(int node) {
        stack.push(node);
        if(minStack.isEmpty()) {
            minStack.push(node);
        }else {
            int tmp=minStack.peek();
            if(node<tmp) {
                minStack.push(node);
            }else {
                minStack.push(tmp);
            }
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
