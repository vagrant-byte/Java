public class countPrimeSetBits {
    //二进制表示中质数个计算置位
    public static int countPrimeSetBits(int left, int right) {
        int count=0;
        for (int i = left; i <=right ; i++) {
            if(isSetBits(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isSetBits(int i) {
        int count=0;
        while (i!=0) {
            i-=(i&(-i));
            count++;
        }
        if(count<2) {
            return false;
        }
        for (int j = 2; j <=count/j ; j++) {
            if(count%j==0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int left=6;
        int right=10;
        int v=countPrimeSetBits(left, right);
        System.out.println(v);
    }
}
