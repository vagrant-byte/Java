import java.util.Scanner;

public class Day31 {
    //因子因数
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int n=scanner.nextInt();
            System.out.print(n+"=");
            int i=2;
            while (i<=n){
              if(n==i) {
                  System.out.println(i);
                  break;
              } else if(n%i==0) {
                  System.out.print(i+"*");
                  n/=i;
              } else {
                  i++;
              }
            }
        }
    }
    public static int day_of_week(int year,int month,int day) {
        if(month==1||month==2) {
            //月大于等于3小于等于14，1 2 月看作上一年
            month+=12;
            year-=1;
        }
        int c=year/100;//世纪
        year=year%100;//年
        int week=year+(year/4)+(c/4)-2*c+26*(month+1)/10+day-1;
        week=(week%7+7)%7;
        if (week==0) {
            week=7;
        }
        return week;
    }
    public static int day_of_demand(int year,int month,int count,int day_of_week) {
        //count 第几个星期
        int week=day_of_week(year,month,1);
        int day=1+(count-1)*7+((7-week)+day_of_week)%7;
        return day;
    }
    public static void men_day(int year) {
        int week=day_of_week(year,6,1);
        int d=(week==1)?6:(week-2);
        int day=31-d;
        System.out.printf("%d-05-%02d \n",year,day);
    }
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int year=scanner.nextInt();
            System.out.printf("%d-01-01 \n",year);
            System.out.printf("%d-01-%02d \n",year,day_of_demand(year,1,3,1));
            System.out.printf("%d-02-%02d \n",year,day_of_demand(year,2,3,1));
            men_day(year);
            System.out.println(year+"-07-04 ");
            System.out.printf("%d-09-%02d \n",year,day_of_demand(year,9,1,1));
            System.out.printf("%d-11-%02d \n",year,day_of_demand(year,11,4,4));
            System.out.println(year+"-12-25 ");
        }
    }
}
