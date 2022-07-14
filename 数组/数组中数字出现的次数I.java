package 数组;

public class 数组中数字出现的次数I {
    public static int[] singleNumbers(int[] nums) {
        int num=nums[0];
        for (int i = 1; i <nums.length ; i++) {
            num^=nums[i];
        }
        int index=1;
        while ((num&index)==0) {
            index++;
        }
//        int index=num&(-num);
        int[] res=new int[2];
        for (int tmp:nums) {
            if((tmp&index)==0) {
                res[0]^=tmp;
            }else {
                res[1]^=tmp;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a={1,2,5,2};
        int[] r=singleNumbers(a);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
    }
}
