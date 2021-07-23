import java.util.Arrays;

public class testSort {
    //直接插入排序
    //时间复杂度  最好O(N）  最坏O(N^2)
    //空间复杂度  O(1)
    //稳定性     稳定
    public static void insertSort(int[] array) {
        for (int i = 1; i <array.length ; i++) {
            int tmp=array[i];
            int j = i-1;
            for (; j >=0 ; j--) {
                if(array[j]>tmp) {
                    array[j+1]=array[j];
                    array[j]=tmp;
                } else {
                    break;
                }
            }
            array[j+1]=tmp;
        }

    }

    public static void main1(String[] args) {
        int[] a={3,2,5,1,4};
        insertSort(a);
        System.out.println(Arrays.toString(a));
    }
    public String name="abc";
    public static void main(String[] args) {
      
    }
}
