import java.util.Arrays;
import java.util.Scanner;

public class Day14 {
    //幸运的袋子
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] list=new int[n];
        for (int i = 0; i <n; i++) {
            list[i]=scanner.nextInt();
        }
        Arrays.sort(list);
        int sum=0;
        int multi=1;
        int num=find(list,0,sum,multi);
        int num1=count(list,0,sum,multi);
        System.out.println(num);
        System.out.println(num1);

    }
    private static int find(int[] arr, int index, long sum, int multi) {
        int count = 0;
        for (int i = index; i < arr.length; i++) {
            sum += arr[i];//求和
            multi *= arr[i];//求积
            if (sum > multi) {
                count += 1 + find(arr, i + 1, sum, multi);//继续遍历下一个数
            } else if (arr[i] == 1) {//若arr[i]=1，1和任何数的和>1和任何数的积
                count += find(arr, i + 1, sum, multi);
            } else {
                break;//说明不构成幸运，直接break
            }
            //回溯
            sum -= arr[i];
            multi /= arr[i];

            //因为相同的无区别，跳过重复的号码
            while (i < arr.length - 1 && arr[i] == arr[i + 1]) {
                i++;
            }
        }
        return count;
    }

    private static int count(int[] list,int pos, int sum,int product) {
        int num=0;
        for (int i = pos; i <list.length; i++) {
            sum += list[i];
            product *= list[i];
            if(sum > product) {
                num += 1+ count(list,i+1,sum,product);
            } else if (list[i]==1) {
                num += count(list,i+1,sum,product);
            } else {
                break;
            }
            sum -= list[i];
             product /= list[i];
            while (list[i]==list[i+1]&&i<list.length-1) {
                i++;
            }
        }
        return num;
    }


    //计算日期到天数转换
    public static void main2(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] days={31,28,31,30,31,30,31,31,30,31,30,31};
        while (scanner.hasNext()) {
            int year=scanner.nextInt();
            int month=scanner.nextInt();
            int day=scanner.nextInt();
            int sum=0;
            for (int i = 0; i <month-1 ; i++) {
                sum+=days[i];
            }
            if(year%4==0&&year%100!=0) {
                sum+=1;
            }
            sum+=day;
            System.out.println(sum);
        }
    }
    //下一个更大元素
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result=new int[nums1.length];
        for (int i = 0; i <nums1.length ; i++) {
            int j=0;
            while (j<nums2.length&&nums1[i]!=nums2[j]) {
                j++;
            }
            while (j<nums2.length&&nums1[i]>=nums2[j]) {
                j++;
            }
            if(j>=nums2.length) {
                result[i]=-1;
            } else {
                result[i]=nums2[j];
            }
        }
        return result;
    }

    public static void main1(String[] args) {
        int[] num1={4,1,2};
        int[] num2={1,3,4,2};
        int[] result=nextGreaterElement(num1,num2);
        for (int i = 0; i <result.length ; i++) {
            System.out.print(result[i]);
        }

    }
}
