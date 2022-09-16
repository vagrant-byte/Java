public class 位运算 {
    //136.只出现一次的数字
    public int singleNumber(int[] nums) {
        int ero=0;
        for(int i=0;i<nums.length;i++) {
            ero=ero^nums[i];
        }
        return ero;
    }
    //260.只出现一次的数字II
    public int[] singleNumber1(int[] nums) {
        int[] arr=new int[2];
        int ero=0;
        for(int i=0;i<nums.length;i++) {
            ero=ero^nums[i];
        }
        int leftOne=ero&(~ero+1);
        int ero1=0;
        for(int i=0;i<nums.length;i++) {
            if((nums[i]&leftOne)==0) {
                ero1=ero1^nums[i];
            }
        }
        arr[0]=ero1;
        arr[1]=ero^ero1;
        return arr;
    }
    //保证参数n，不是1就是0
    //1->0
    //0->1
    public int flip(int n) {
        return n^1;
    }
    //n 是非负数 返回1
    //n 是负数  返回0
    public int sign(int n) {
        return flip((n>>31)&1);
    }
    public int getMax(int a,int b) {
        int c=a-b;
        int scA=sign(c);//a-b非负，scA为1， a-b为负，scA为1
        int scB=flip(scA);//scA为0，scB为1，scA为1，scB为0
        //scA为0，scB必为1.scA为1，scB必为0
        return a*scA+b*scB;
    }
    public int getMax1(int a,int b) {
        int c=a-b;
        int sa=sign(a);//a的符号
        int sb=sign(b);//b的符号
        int sc=sign(c);//a-b的符号
        int difSab=sa^sb;//a和b的符号不一样，为1，一样为0
        int sameSab=flip(difSab);//a和b符号一样为1，不一样为0
        //返回a ，a和b符号相同and a-b>=0  or  a和b符号不同，且a>0
        int returnA=difSab*sa+sameSab*sc;
        int returnB=flip(returnA);
        return a*returnA+b*returnB;
    }
    //判断一个32位正数是不是2的幂，4的幂
    //2的幂，二进制中只能有一位是1  n&(~(n+1))拿到最右侧的1
    //n只有一个1，n&(n-1)==0,即可证明一个n是2的幂
    //如n=4，二进制00000100，其-1，为00000011，其二进制和-1后的二进制&==0，即可证明n是2的幂
    //判断是4的幂，首先判断只能有1个1，且1的位置在偶数位，因此先判断是否有1个1，其次是值&01010101不等于0是4的幂等于0不是4的幂
    public boolean is2Power(int n) {
        return (n&(n-1))==0;
    }
    public boolean isPowerOfTwo(int n) {
        int tmp=n&(~n+1);
        return (n&tmp)==n;
    }
    public boolean is4Power(int n) {
        //                      01010101
        return (n&(n-1))==0&&(n&0x55555555)!=0;
    }
    //给定两个有符号的32位整数a和b，不能使用算数运算符，分别实现a和b的加，减，乘，除运算
    public int add(int a, int b) {
        int sum=a;
        while(b!=0) {
            sum=a^b;
            b=(a&b)<<1;
            a=sum;
        }
        return sum;
    }
    //减法，a+b的相反数，相反数就是其取反+1；
    public int negNum(int n) {
        return add(~n,1);
    }
    //减
    public int minus(int a,int b) {
        return add(a,negNum(b));
    }
    //乘
    public int multi(int a,int b) {
        int res=0;
        while (b!=0) {
            if((b&1)!=0) {
                res=add(res,a);
            }
            a<<=1;
            b>>>=1;
        }

        return res;
    }

}
