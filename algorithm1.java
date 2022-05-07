package 数组;

public class algorithm1 {
    //1.二维数组查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //特殊值判断
        if(matrix==null||matrix.length==0) {
            return false;
        }
        //表示右上角值的行列坐标
        int i=0;
        int j=matrix[0].length-1;
        while(i<matrix.length&&j>=0) {
            //目标值小于当前值，列--
            if(matrix[i][j]>target) {
                j--;
            }else if(matrix[i][j]<target) {
                //目标值大于当前值 行++
                i++;
            }else{
                //目标值等于当前值
                return true;
            }
        }
        return false;
    }
    //旋转数组的最小数字
    public static int minArray(int[] numbers) {
       int left=0;
       int right=numbers.length-1;
       int mid=0;
       while (numbers[left]>=numbers[right]) {
           //此条件可以判读数组是否进行了循环，只有循环后才会出现两个有序的数组，否则返回其0下标的值
           if(right-left==1) {
               //left于right相邻时返回right所对应下标的值
               mid=right;
               break;
           }
           //右移相当于除2
           mid=left+((right-left)>>1);
           if(numbers[left]==numbers[mid]&&numbers[mid]==numbers[right]) {
               //left right mid 对应下标的值相等，线性遍历
               int num=numbers[left];
               for (int i = left+1; i <numbers.length ; i++) {
                   if(num>numbers[i]) {
                       num=numbers[i];
                   }
               }
               return num;
           }
           if(numbers[left]<=numbers[mid]) {
               //mid的左半部分是非递减的，最小值在mid的右侧区域
               left=mid;
           }else {
               //mid的右半部分是非递减的，最小值在mid的左侧区域
               right=mid;
           }
       }
       return numbers[mid];
    }
    //调整数组顺序使奇数位于偶数前面
    public int[] exchange(int[] nums) {
        if(nums==null||nums.length==0) {
            return new int[]{};
        }
        int k=0;
        for (int i = 0; i <nums.length ; i++) {
            //[0,i]已排序区间，[i,nums.length]未排序区间
            if(nums[i]%2==1) {//从左向右 每次遇到都是最前面的奇数
                //奇数
                int tmp=nums[i];//保存当前奇数
                int j=i;
                while (j>k) {//将该奇数之前的内容(偶数序列)整体后移一位
                    nums[j]=nums[j-1];
                    j--;
                }
                nums[k++]=tmp;//将当前奇数放入k下标处
            }
        }
        return nums;

    }

    public static void main(String[] args) {
        int[] n={1,3,5};
        int num=minArray(n);
        System.out.println(num);
    }
}
