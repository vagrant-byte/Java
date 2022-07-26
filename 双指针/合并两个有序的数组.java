package 双指针;

public class 合并两个有序的数组 {
    public static void merge(int A[], int m, int B[], int n) {
        int len1=m-1;
        int len2=n-1;
        int index=m+n-1;
        while (len1>=0&&len2>=0) {
            if(A[len1]>B[len2]) {
                A[index--]=A[len1--];
            }else {
                A[index--]=B[len2--];
            }
        }
        while (len2>=0) {
            A[index--]=B[len2--];
        }
    }

    public static void main(String[] args) {
        int[] A={4,5,6,0,0,0};
        int[] B={1,2,3};
        int m=3;
        int n=3;
        merge(A,m,B,n);
    }
}
