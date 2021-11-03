import java.util.ArrayList;
import java.util.Scanner;

public class Day21 {
    //MP3光标移到
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();//歌曲总数
        String command=scanner.next();//命令
        char[] chars=command.toCharArray();
        while (n!=0) {
            int num=1;//光标当前歌曲编号
            int first=1;//当前屏幕显示页的第一首歌
            if (n<=4) {
                for (int i = 0; i <chars.length ; i++) {
                    if(num==1&&chars[i]=='U') {
                        num=n;
                    }else if(num==n&&chars[i]=='D') {
                        num=1;
                    }else if(chars[i]=='U') {
                        num--;
                    } else {
                        num++;
                    }
                }
                for (int i = 1; i <n ; i++) {
                    System.out.print(i+" ");
                }
                System.out.println(n);
                System.out.println(num);
                return;
            } else {
                for (int i = 0; i <chars.length ; i++) {
                    //特殊翻页
                    if(first==1&&num==1&&chars[i]=='U') {
                        num=n;
                        first=n-3;
                    } else if(first==n-3&&num==n&&chars[i]=='D') {
                        num=1;
                        first=1;
                    } else if(first!=1&&num==first&&chars[i]=='U') {//一般翻页
                        num--;
                        first--;
                    } else if(first!=n-3&&num==first+3&&chars[i]=='D') {
                        num++;
                        first++;
                    } else if(chars[i]=='U') {
                        num--;
                    } else {
                        num++;
                    }
                }
                for (int i = first; i <first+3 ; i++) {
                    System.out.print(i+" ");
                }
                System.out.println(first+3);
                System.out.println(num);
                return;
            }
        }

    }

    //洗牌
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int T=scanner.nextInt();
        for (int i = 0; i <T ; i++) {
            int n=scanner.nextInt();
            int k=scanner.nextInt();
            int[] arr=new int[n*2];

            for (int j = 0; j <2*n ; j++) {
                arr[j]=scanner.nextInt();
            }
            //交换
            for (int j = 0; j <k ; j++) {
                swap(arr,arr.length);
            }
            //打印
            for (int j = 0; j <2*n-1 ; j++) {
                System.out.print(arr[j]+" ");
            }
            System.out.println(arr[2*n-1]);
        }
    }

    private static void swap(int[] arr, int length) {
        ArrayList<Integer> list=new ArrayList<>();
        for (int i = 0; i <length/2 ; i++) {
            list.add(arr[i]);
            list.add(arr[i+length/2]);
        }
        for (int i = 0; i <length ; i++) {
            arr[i]=list.get(i);
        }
    }
}
