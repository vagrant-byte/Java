//接雨水
public class trap {
    public int trap(int[] height) {
        int sum=0;
        for (int i = 1; i <height.length-1 ; i++) {
            int leftMax=0;
            int rightMax=0;
            //左边元素的最大值
            for (int j = i; j>=0  ; j--) {
                leftMax=Math.max(leftMax,height[j]);
            }
            //右边元素最大值
            for (int j = i; j <height.length ; j++) {
                rightMax=Math.max(rightMax,height[j]);
            }
            sum+=Math.min(leftMax,rightMax)-height[i];
        }
        return sum;
    }
}
