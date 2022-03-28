import java.util.Arrays;

public class dailyTemperatures {
    //每日温度
    public static int[] dailyTemperatures(int[] temperatures) {
        int len=temperatures.length;
        int[] res=new int[len];
        for (int i = 0; i <len-1 ; i++) {
            for (int j = i+1; j <len ; j++) {
                if(temperatures[j]>temperatures[i]) {
                    res[i]=j-i;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a=new int[]{73,74,75,71,69,72,76,73};
        int[] r=dailyTemperatures(a);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
     }
}
