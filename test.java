import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    //存在重复元素
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);//先排序
        for(int i=0;i<nums.length-1;i++) {
            //比较
            if(nums[i]==nums[i+1]) {
                return true;
            }
        }
        return false;

    }
    //最大子序和
    public int maxSubArray(int[] nums) {
        int ret=nums[0];
        int sum=0;
        for(int i=0;i<nums.length;i++) {
            if(sum>0) {
                sum+=nums[i];
            } else {
                sum=nums[i];
            }
            ret=ret>sum?ret:sum;
        }
        return ret;

    }
    //两数之和
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            for(int j=i+1;j<nums.length;j++) {
                if(nums[i]+nums[j]==target) {
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {};

    }
    //合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p=m+n-1;
        int i=m-1;
        int j=n-1;
        while(i>=0&&j>=0) {
            //由后向前比较num1和num2中的元素将较大的一个放入num1数组的最后边
            //三目运算符巧用   如果num1的最后一个比较大则将num1的最后一个放入数组最后，num1倒数第二个元素继续和num2的最后一个元素比较
            nums1[p--]=nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        while(j>=0) {
            //num1中元素比较完，将num2的其余元素放入num1后
            nums1[p--]=nums2[j--];
        }
    }
    //两个数组的交集
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list=new ArrayList<>();
        int i=0;
        int j=0;
        while (i<nums1.length&&j<nums2.length) {
            if(nums1[i]>nums2[j]) {
                j++;
            }else if(nums1[i]<nums2[j]) {
                i++;
            }else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] arr=new int[list.size()];
        for (int k = 0; k < list.size() ; k++) {
            arr[k]=list.get(k);
        }
        return arr;
    }
    //买股票的最佳时间
    //输入：[7,1,5,3,6,4]
    //输出：5
    //解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5
    public static int maxProfit(int[] prices) {
        /*int min=prices[0];
        int count=0;
        for (int i = 0; i <prices.length ; i++) {
            if(prices[i]<min) {
                min=prices[i];
                count=i;
            }
        }
        if(count==prices.length-1) {
            return 0;
        }
        int max=prices[count];
        for (int i = count; i <prices.length ; i++) {
            if(prices[i]>max) {
                max=prices[i];
            }
        }
        int num=max-min;
        return num;*/
        int min=prices[0];
        int max=0;
        for (int i = 0; i <prices.length ; i++) {
            if(prices[i]<min) {
                min=prices[i];
            }
            if(prices[i]-min>max) {
                max=prices[i]-min;
            }
        }
        return max;
    }
    //重塑矩阵
    //输入:
    //nums =
    //[[1,2],
    // [3,4]]
    //r = 1, c = 4
    //输出:
    //[[1,2,3,4]]
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m=mat.length;
        int n=mat[0].length;
        if(m*n!=r*c) {
            return mat;
        }
      //先存放在一维数组中
        int[] tmp=new int[r*c];
        int count=0;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                tmp[count]=mat[i][j];
                count++;
            }
        }
        count=0;
        //用二维数组接收
        int[][] arr=new int[r][c];
        for (int i = 0; i <r ; i++) {
            for (int j = 0; j <c ; j++) {
                arr[i][j]=tmp[count];
                count++;
            }
        }
        return arr;
    }
    //杨辉三角
    public List<List<Integer>> generate(int numRows) {
        if(numRows==0) {
            return null;
        }
        List<List<Integer>> list=new ArrayList<>();
        //处理第一行
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list.add(list1);
        for (int i = 1; i <numRows ; i++) {
            //上一行
            List<Integer> prev=list.get(i-1);
            //处理每一行的第一个元素
            List<Integer> cur=new ArrayList<>();
            cur.add(1);
            for (int j = 1; j <i ; j++) {
                int c=prev.get(j-1)+prev.get(j);
                cur.add(c);
            }
            //处理每一行的最后一个元素
            cur.add(1);
            list.add(cur);
        }
        return list;
    }
    //有效的数独
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row=new boolean[9][9];
        boolean[][] col=new boolean[9][9];
        boolean[][] block=new boolean[9][9];
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <9 ; j++) {
                if(board[i][j]!='.') {
                    int num=board[i][j]-'1';
                    int blockIndex=i/3*3+j/3;
                    if(row[i][num]||col[j][num]||block[blockIndex][num]) {
                        return false;
                    } else {
                        row[i][num]=true;
                        col[j][num]=true;
                        block[blockIndex][num]=true;
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] arr1=new int[] {7,4,5,3,6,1};
        System.out.println(maxProfit(arr1));


    }
}
