public class 位运算 {
    //只出现一次的数字
    public int singleNumber(int[] nums) {
        int ero=0;
        for(int i=0;i<nums.length;i++) {
            ero=ero^nums[i];
        }
        return ero;
    }
    //只出现一次的数字II
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
}
