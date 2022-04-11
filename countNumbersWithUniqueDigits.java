//统计各位数字都不同的数字个数
public class countNumbersWithUniqueDigits {
    public static int countNumbersWithUniqueDigits(int n) {
        //考虑首位不为0，第一位能选 1~9，9个，第二位能选 0~9 中处了第一位以外的 9 个，第三位 8 个。。以此类推
        //考虑首位为 0，相当于 n-1 的情况，在前面已经计算过了
        //所以最终结果就是两者相加
        //n = 0 ：1
        //n = 1 ：9 + 1
        //n = 2 ：9*9 + 10
        //n = 3 ：9*9*8 + 91
        int res=1;
        int p=9;
        for (int i = 1; i <=n&&i<10 ; i++) {
            res+=p;
            p*=(10-i);
        }
        return res;
//        if(n==0) {
//            return 1;
//        }else if(n==1) {
//            return 10;
//        } else if(n==2) {
//            return 91;
//        } else if(n==3){
//            return 739;
//        } else if(n==4) {
//            return 5275;
//        } else if(n==5) {
//            return 32491;
//        } else if(n==6) {
//            return 168571;
//        } else if (n==7) {
//            return 712891;
//        } else if(n==8) {
//            return 2345851;
//        }
//        return 0;
    }

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(3));
    }
}
