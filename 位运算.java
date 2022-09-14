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
}
