import java.util.Scanner;

public class Day10 {
    //密码等级
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int sum=0;
            String str=scanner.nextLine();
            int length=str.length();
            int numA=0;//记录大写字母
            int numa=0;//记录小写字母
            int num=0;//记录数字
            int top=0;//记录符号
            for (int i = 0; i <length ; i++) {
                if(str.charAt(i)>=48&&str.charAt(i)<=57) {
                    num++;
                }else if(str.charAt(i)>=65&&str.charAt(i)<=90) {
                    numA++;
                }else if(str.charAt(i)>=97&&str.charAt(i)<=122) {
                    numa++;
                }else if((str.charAt(i)>=33&&str.charAt(i)<=47)||(str.charAt(i)>=90&&str.charAt(i)<=96)||(str.charAt(i)>=123&&str.charAt(i)<=126)) {
                    top++;
                }
            }
            if(length<=4) {
                sum+=5;
            } else if(length>=8) {
                sum+=25;
            } else {
                sum+=10;
            }
            if((numA!=0&&numa==0)||(numA==0&&numa!=0)) {
                sum+=10;
            } else if(numA!=0&&numa!=0) {
                sum+=20;
            }
            if(num==1) {
                sum+=10;
            } else if(num>1) {
                sum+=20;
            }
            if(top==1) {
                sum+=10;
            } else if(top>1) {
                sum+=25;
            }
            if((num!=0&&numA!=0)||(num!=0&&numa!=0)) {
                sum+=2;
            } else if(((num!=0&&numA!=0)&&(num!=0&&top!=0))||((num!=0&&numa!=0)&&(num!=0&&top!=0))) {
                sum+=3;
            } else if(num!=0&&numA!=0&&numa!=0&&top!=0) {
                sum+=5;
            }
            if(sum>=90) {
                System.out.println("VERY_SECURE");
            } else if(sum>=80) {
                System.out.println("SECURE");
            } else if(sum>=70) {
                System.out.println("VERY_STRONG");
            } else if(sum>=60) {
                System.out.println("STRONG");
            } else if(sum>=50) {
                System.out.println("AVERAGE");
            } else if(sum>=25) {
                System.out.println("WEAK");
            } else {
                System.out.println("VERY_WEAK");
            }
        }

    }
    //三子棋
    public static boolean board(int[][] arr) {
        if(arr[0][0]==1) {
            if(arr[0][0]+arr[0][1]+arr[0][2]==3||arr[0][0]+arr[1][0]+arr[2][0]==3||arr[0][0]+arr[1][1]+arr[2][2]==3||arr[0][2]+arr[1][1]+arr[2][0]==3) {
                return true;
            }
        } else if(arr[0][0]==-1) {
            if(arr[0][0]+arr[0][1]+arr[0][2]==-3||arr[0][0]+arr[1][0]+arr[2][0]==-3||arr[0][0]+arr[1][1]+arr[2][2]==-3||arr[0][2]+arr[1][1]+arr[2][0]==-3) {
                return true;
            }
        } else if(arr[1][1]==1) {
            if(arr[0][1]+arr[1][1]+arr[2][1]==3||arr[1][0]+arr[1][1]+arr[1][2]==3) {
                return true;
            }
        } else if(arr[1][1]==-1) {
            if(arr[0][1]+arr[1][1]+arr[2][1]==3||arr[1][0]+arr[1][1]+arr[1][2]==3) {
                return true;
            }
        }
        return false;
    }

}
