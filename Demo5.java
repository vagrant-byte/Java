package 算法第一天;

import java.util.ArrayList;

public class Demo5 {
    //数组中只出现一次的数字
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if(array==null||array.length==0||num1==null||num2==null) {
            return;
        }
        //整体异或
        int result=array[0];
        for (int i = 1; i <array.length ; i++) {
            result^=array[i];
        }
        //分组条件
        int size=Integer.SIZE;
        int flag=1;
        for (int i = 0; i <size ; i++) {
            if(((flag<<i)&result)!=0) {
                flag<<=i;
                break;
            }
        }
        //进行分组
        num1[0]=0;
        num2[0]=0;
        for (int i = 0; i <array.length ; i++) {
            if((array[i]&flag)==0) {
                num1[0]^=array[i];
            } else {
                num2[0]^=array[i];
            }
        }
    }

    //和为S的连续正数序列
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        //low 和 high 即表示下标也表示值
        int low=1;
        int high=2;
        while (low<high) {
            //等差数列求和公司（a1+an)*n/2
            int total=(low+high)*(high-low+1)/2;
            if(total==sum) {
                ArrayList<Integer> tmp=new ArrayList<>();
                for (int i = low; i <=high; i++) {
                    tmp.add(i);
                }
                result.add(tmp);
                low++;
            } else if(total<sum) {
                high++;
            } else {
                low++;
            }
        }
        return result;
    }

    //左旋字符串
    private void swap(char[] arr,int left,int right) {
        while (left<right) {
            char tmp=arr[left];
            arr[left]=arr[right];
            arr[right]=tmp;
            left++;
            right--;
        }
    }
    public String LeftRotateString(String str,int n) {
        if(str.length()==0) {
            return str;
        }
        char[] chars=str.toCharArray();
        n%=str.length();
        swap(chars,0,n-1);
        swap(chars,n,str.length()-1);
        swap(chars,0,str.length()-1);
        return new String(chars);
    }
}
