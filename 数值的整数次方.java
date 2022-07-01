import org.omg.CORBA.MARSHAL;

public class 数值的整数次方 {
    //当我们要计算 x^n 我们可以先递归地计算出 y = x^{n/2 }其中⌊a⌋ 表示对 a 进行下取整；
    //
    //根据递归计算的结果，如果 nn 为偶数，那么 x^n = y^2如果 n 为奇数，那么 x^n = y^2 *x
    //递归的边界为 n ==0，任意数的 0次方均为 1
    //x→x^2→x^4→x^9→x^19→x^38→x^77
    public static double myPow(double x, int n) {
        //快速幂
        if(n==0) {
            return 1;
        }
        if(n==1) {
            return x;
        }
        if(n==-1) {
            return 1/x;
        }
       double half=myPow(x,n/2);
        double mod=myPow(x,n%2);
        return half*half*mod;
//        if(x==1) {
//            return 1;
//        }else if(x==-1) {
//            return n%2==0? 1:-1;
//        } else if(x==0&&n==0) {
//           return 0;
//       }else if(x!=0&&n==0) {
//           return 1;
//       }else if(x>0&&n>0) {
//           double sum=1;
//           while (n>0) {
//               sum*=x;
//               n--;
//           }
//           return sum;
//       }else if(x>0&&n<0) {
//           n=n*(-1);
//           x=1/x;
//           double sum=1;
//           while (n>0) {
//               sum*=x;
//               n--;
//           }
//           return sum;
//       } else if(x<0&&n<0) {
//           x=1/x;
//           n=n*(-1);
//           double sum=1;
//           while (n>0) {
//               sum*=x;
//               n--;
//           }
//           return sum;
//       } else {
//           double sum=1;
//           while (n>0) {
//               sum*=x;
//               n--;
//           }
//           return sum;
//       }
    }

    public static void main(String[] args) {
        double x=-2.00000;
        int n=-2;
        double m=myPow(x, n);
        System.out.println(m);
    }
}
