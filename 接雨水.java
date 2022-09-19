package 动态规划;

import java.util.Stack;

public class 接雨水 {
    public static int trap(int[] height) {
        Stack<Integer> stack=new Stack<>();
        int sum=0;
        for (int i = 0; i <height.length ; i++) {
            while (!stack.isEmpty()&&height[stack.peek()]<height[i]) {
                int index=stack.pop();
                if(stack.isEmpty()) {
                    break;
                }
                int left=stack.peek();
                int currWidth=i-left-1;
                int currHeight=Math.min(height[left],height[i])-height[index];
                sum+=currWidth*currHeight;
            }
            stack.push(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] h={0,1,0,2,1,0,1,3,2,1,2,1};
        int s=trap(h);
        System.out.println(s);
    }
}
