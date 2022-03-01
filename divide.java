package 二进制;

import java.util.HashMap;

public class divide {
    //整数除法
    public static int divide1(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        if (a > 0) {
            a = -a;
        }
        if (b > 0) {
            b = -b;
        }
        int count = 0;
        while (a <= b) {
            a -= b;
            count++;
        }
        return sign == 1 ? count : -count;
    }

    public static int divide2(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            if ((a >>> i) - b >= 0) {
                a -= (b << i);
                count += (1 << i);
            }
        }
        return sign == 1 ? count : -count;
    }

    //前n个数字二进制中1的个数
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

    //只出现一次的数字
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 1) {
                return nums[i];
            }
        }
        return -1;
    }
    //单词长度的最大乘积
    public int maxProduct(String[] words) {
        int len=words.length;
        int[] nums=new int[len];
        //将单词的二进制进行存储，如果两个字符串不包含相同的单词则按位与为0
        for (int i = 0; i <len ; i++) {
            for (int j = 0; j <words[i].length() ; j++) {
                nums[i]|=(1<<(words[i].charAt(j)-'a'));
            }
        }
        int res=0;
        for (int i = 0; i <len-1 ; i++) {
            for (int j = i+1; j <len ; j++) {
                if((nums[i]&nums[j])==0) {
                    res=Math.max(res,words[i].length()*words[j].length());
                }
            }
        }
        return res;
    }
}

