package 排序;

public class 数组中的逆序对 {
    public static int InversePairs(int [] array) {
        int i=0;
        int index=0;
        while (i<array.length-1) {
            int right=array.length-1;
            while (right>0&&i<right) {
                if(array[i]>array[right]) {
                    index++;
                }
                right--;
            }
            i++;
        }
        return index%1000000007;
    }
}
