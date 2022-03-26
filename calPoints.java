import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//棒球比赛
public class calPoints {
    public int calPoints(String[] ops) {
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i <ops.length ; i++) {
            if(ops[i].equals("+")) {
                int a=stack.pop();
                int b=stack.peek();
                stack.push(a);
                stack.push(a+b);
            }else if(ops[i].equals("D")) {
                stack.push(stack.peek()*2);
            }else if(ops[i].equals("C")) {
                stack.pop();
            } else {
                stack.push(Integer.parseInt(ops[i]));
            }
        }
        int sum=0;
        while (!stack.isEmpty()) {
            sum+=stack.pop();
        }
        return sum;

    }
}
