package 算法基础;

import java.util.Arrays;
import java.util.HashMap;

public class algorithm {
    //二分查找
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        while(left<=right) {
            int mid=left+(right-left)/2;
            if(nums[mid]<target) {
                left=mid+1;
            } else if(nums[mid]>target) {
                right=mid-1;
            } else{
                return mid;
            }
        }
        return -1;
    }
    //搜素插入位置
    public int searchInsert(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            if(nums[i]>=target) {
                return i;
            }
        }
        return nums.length;
    }
    /*二分找分界点 第一个错误版本；
    public int firstBadVersion(int n) {
        int left=1;
        int right=n;
        while(left<right) {
            int mid=left+(right-left)/2;
            if(isBadVersion(mid)) {
                right=mid;
            } else {
                left=mid+1;
            }
        }
        return left;
    }*/
    //有序数组的平方 暴力求解
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i <nums.length ; i++) {
            nums[i]=nums[i]*nums[i];
        }
        Arrays.sort(nums);

        return nums;

    }
    //有序数组的平方 双指针
    public int[] sortedSquares1(int[] nums) {
        int le=0;
        int ri=nums.length-1;
        int [] arr=new int[nums.length];
        int j=nums.length-1;
        while (le<=ri) {
            if(nums[le]*nums[le]<nums[ri]*nums[ri]) {
                arr[j]=nums[ri]*nums[ri];
                j--;
                ri--;
            } else {
                arr[j]=nums[le]*nums[le];
                j--;
                le++;
            }
        }
        return arr;
    }
    //旋转数组  前一部分旋转 后一部分旋转 整体旋转
    public void swap(int[] a,int be,int ed) {
        while (be<ed) {
            int tmp=a[be];
            a[be]=a[ed];
            a[ed]=tmp;
            be++;
            ed--;
        }
    }
    public void rotate(int[] nums, int k) {
            k=k%nums.length;
            swap(nums,0,nums.length-1);
            swap(nums,0,k-1);
            swap(nums,k,nums.length-1);
    }
    //移动零
    public void moveZeroes(int[] nums) {
        int index=0;
        for (int i = 0; i <nums.length ; i++) {
            if(nums[i]!=0) {
                nums[index]=nums[i];
                index++;
            }
        }
        for (int i = index; i <nums.length ; i++) {
            nums[i]=0;
        }
    }
    //两数之和
    public int[] twoSum(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;
        while (left<right) {
            if(numbers[left]+numbers[right]==target) {
                return new int[] {left+1,right+1};
            } else if(numbers[left]+numbers[right]<target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
    //反转字符串
    public void reverseString(char[] s) {
        int left=0;
        int right=s.length-1;
        while (left<right) {
            char tmp=s[left];
            s[left]=s[right];
            s[right]=tmp;
            left++;
            right--;
        }
    }
    //反转字符串中单词
    public String reverseWords(String s) {
        String[] str=s.split(" ");
        StringBuffer stringBuffer=new StringBuffer();
        for (String s1:str) {
            char[] chars=s1.toCharArray();
            reverseString(chars);
            stringBuffer.append(String.valueOf(chars)+" ");
        }
        String s1=stringBuffer.toString();
        return s1.substring(0,s1.length()-1);
    }
    //无重复字符串的最长字串
    public int lengthOfLongestSubstring(String s) {
        int slow=0;
        int max=0;
        HashMap<Character,Integer> map=new HashMap<>();
        for (int i = 0; i <s.length() ; i++) {
            if(map.containsKey(s.charAt(i))) {
                slow=Math.max(slow,map.get(s.charAt(i))+1);

            }
            map.put(s.charAt(i),i);
            max=Math.max(max,i-slow+1);
        }
        return max;
    }
    //图像渲染
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        DFS(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public void DFS(int[][] image, int i, int j, int oldColor, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length) {
            return;
        }
        if (image[i][j] != oldColor) {
            return;
        }
        image[i][j] = newColor;
        DFS(image, i + 1, j, oldColor, newColor);
        DFS(image, i, j + 1, oldColor, newColor);
        DFS(image, i - 1, j, oldColor, newColor);
        DFS(image, i, j - 1, oldColor, newColor);
    }
    //岛屿最大面积
    public int maxAreaOfIsland(int[][] grid) {
        int max=0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1) {
                    max=Math.max(def(grid,i,j),max);
                }
            }
        }
        return max;
    }
    public int def(int[][] grid,int i,int j) {
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) {
            return 0;
        }
        int count=1;
        grid[i][j]=0;
        count+=def(grid,i+1,j);
        count+=def(grid,i-1,j);
        count+=def(grid,i,j+1);
        count+=def(grid,i,j-1);
        return count;
    }
    //合并两个二叉树
    /*public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1==null&&root2==null) {
            return null;
        }
        if(root1==null) {
            return root2;
        }
        if(root2==null) {
            return root1;
        }
        root1.val+=root2.val;
        root1.left=mergeTrees(root1.left,root2.left);
        root1.right=mergeTrees(root1.right,root2.right);
        return root1;
    }
    //填充每个节点的下一个节点的右侧指针
     public Node connect(Node root) {
        if(root==null) {
            return null;
        }
        if(root.left!=null) {
            root.left.next=root.right;
            if(root.next!=null) {
            root.right.next=root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
    */
    //动态规划
    //爬楼梯
    public int climbStairs(int n) {
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 2;
        }
        int i1=1;
        int i2=2;
        for(int i=3;i<=n;i++) {
            int tmp=i1+i2;
            i1=i2;
            i2=tmp;
        }
        return i2;
    }
    //打家劫舍
    public int rob(int[] nums) {
        if(nums==null) return 0;
        if(nums.length==1) return nums[0];
        int[] res=new int[nums.length];
        res[0]=nums[0];
        res[1]=Math.max(res[0],nums[1]);
        for(int i=2;i<nums.length;i++) {
            res[i]=Math.max(res[i-1],res[i-2]+nums[i]);
        }
        return res[res.length-1];

    }
    //位运算
    //位1的个数
    public int hammingWeight(int n) {
        int count = 0;
        while(n!=0){
            n = ((n-1)&n);
            count++;
        }
        return count;
    }
    //2的幂
    public boolean isPowerOfTwo(int n) {
        return (n>0)&&((n&(n-1))==0);

    }
    //只出现一次的数字
    public int singleNumber(int[] nums) {
        int tmp=0;
        for (int i = 0; i <nums.length ; i++) {
            tmp=tmp^nums[i];
        }
        return tmp;
    }

}
