import java.util.Scanner;

public class Day32 {
    //斐波那契凤尾
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] num=new int[100001];
        num[0]=1;
        num[1]=1;
        for (int i = 2; i <num.length ; i++) {
            num[i] = num[i - 1] + num[i - 2];
            num[i] = num[i] % 1000000;
        }
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            if(n<29) {
                System.out.println(num[n]);
            } else {
                System.out.printf("%06d\n",num[n]);
            }
        }
    }
    //淘宝网店
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int fromYear=scanner.nextInt();
            int fromMonth=scanner.nextInt();
            int fromDay=scanner.nextInt();
            int toYear=scanner.nextInt();
            int toMonth=scanner.nextInt();
            int toDay=scanner.nextInt();
            int[] temp={31,28,31,30,31,30,31,31,30,31,30,31};
            if(fromYear==toYear) {
                if(fromMonth==toMonth) {
                    if (fromMonth == 2 || fromMonth == 3 || fromMonth == 5 || fromMonth == 7 || fromMonth == 11) {
                        System.out.println(toDay - fromDay + 1);
                    } else {
                        System.out.println((toDay-fromDay)*2+2);
                    }
                } else {
                    int sum=0;
                    if (fromMonth == 2 || fromMonth == 3 || fromMonth == 5 || fromMonth == 7 || fromMonth == 11) {
                        sum+=(temp[fromMonth-1]-fromDay)*2;
                    } else {
                        sum+=temp[fromMonth-1]-fromDay;
                    }
                    for (int i = fromMonth+1; i <toMonth ; i++) {
                        if (i == 2 || i == 3 || i == 5 || i == 7 || i == 1) {
                            sum+=temp[i-1]*2;
                        } else {
                            sum+=temp[i-1];
                        }
                     }
                    if (toMonth == 2 || toMonth == 3 || toMonth == 5 || toMonth == 7 || toMonth == 11) {
                        sum+=toDay*2;
                    } else {
                        sum+=toDay;
                    }
                    System.out.println(sum);
                }
            } else {
                int sum=0;
                if((fromYear%4==0&&fromYear%100!=0)||fromYear%400==0) {
                    //开始年
                    if(fromMonth==2) {
                        sum+=29-fromDay+1;
                    }else if (fromMonth == 3 || fromMonth == 5 || fromMonth == 7 || fromMonth == 11) {
                        sum+=(temp[fromMonth-1]-fromDay)*2;
                    } else {
                        sum+=temp[fromMonth-1]-fromDay;
                    }
                    for (int i = fromMonth+1; i <=12 ; i++) {
                        if(i==2) {
                            sum+=29*2;
                        }else if (i == 3 || i == 5 || i == 7 || i == 1) {
                            sum+=temp[i-1]*2;
                        } else {
                            sum+=temp[i-1];
                        }
                    }
                    for (int i = fromYear+1; i <toYear ; i++) {
                        if((fromYear%4==0&&fromYear%100!=0)||fromYear%400==0) {
                            for (int j= 1; j <=12 ; j++) {
                                if (j == 2 || j == 3 || j == 5 || j == 7 || j == 1) {
                                    sum+=temp[i-1]*2;
                                } else {
                                    sum+=temp[i-1];
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
