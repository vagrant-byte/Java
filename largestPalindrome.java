public class largestPalindrome {
    //最大回文数乘积
    public static int largestPalindrome(int n) {
        if(n==1) {
            return 9;
        }
        long max= (long) (Math.pow(10,n)-1);
        for (long i = max; i >0 ; i--) {
            //从小到大构造回文串
            long num=i;
            for (long j = i; j >0 ; j/=10) {
                num=num*10+j%10;
            }
            for (long j = max; j*j >=num ; j--) {
                if(num%j==0) {
                    return (int) (num%1337);
                }
            }
        }
        return -1;

    }


    public static void main(String[] args) {

    }
}
