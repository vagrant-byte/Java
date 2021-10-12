import java.util.Scanner;

public class two2 {
    //排序子序列
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] A_i=new int[n+1];
        //A_i[n]=0;
        for (int i = 0; i <n ; i++) {
            A_i[i]=scanner.nextInt();
        }
        int count=0;
        int i=0;
        for (;i<n;i++){
            if(A_i[i]<A_i[i+1]) {
                while (i<n&&A_i[i]<=A_i[i+1]) {
                    i++;
                }
                count++;
            } else if(i<n&&A_i[i]==A_i[i+1]) {
                continue;
            }else if (A_i[i]>A_i[i+1]){
                while (i<n&&A_i[i]>=A_i[i+1]) {
                    i++;
                }
                count++;
            }
        }
        System.out.println(count);

    }
    //反转字符串
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        String[] strings=s.split(" ");
        StringBuffer stringBuffer=new StringBuffer();
        int length=strings.length-1;
        while (length>=0) {
            stringBuffer.append(strings[length]+" ");
            length--;
        }
        System.out.println(stringBuffer.toString());
    }

}
