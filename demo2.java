import java.util.Stack;

public class demo2 {
    //棒球比赛
    public static int calPoints(String[] ops) {
        int[] arr=new int[ops.length];
        int i=0;
        for (String s:ops) {
            if(s.equals("+")) {
                arr[i]=arr[i-1]+arr[i-2];
                i++;
            } else if(s.equals("D")) {
                arr[i]=2*arr[i-1];
                i++;
            } else if(s.equals("C")) {
                arr[i-1]=0;
                i--;
            } else {
                arr[i]=Integer.valueOf(s);
                i++;
            }
        }
        int sum=0;
        for (int j = 0; j <arr.length ; j++) {
            sum+=arr[j];
        }
        return sum;
    }

    public static void main(String[] args) {
        String[] ops=new String[]{"5","-2","4","C","D","9","+","+"};
        System.out.println(calPoints(ops));
    }
}
