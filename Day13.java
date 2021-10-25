import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        
    }
    //参数解析
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
//            String str=scanner.nextLine();
//            int i=0;
//            List<String> list=new ArrayList<>();
//            while (i<str.length()) {
//                int begin=i;
//                if(str.charAt(i)=='"') {
//                    i++;
//                    while (i<str.length()&&str.charAt(i)!='"') {
//                        i++;
//                    }
//                    String tmp=str.substring(begin,i-begin+1);
//                    list.add(tmp);
//                }
//                begin=i;
//                while (i<str.length()&&str.charAt(i)!=' ') {
//                    i++;
//                }
//                if(i<str.length()) {
//                    String tmp=str.substring(begin,i-begin);
//                    list.add(tmp);
//                    i++;
//                } else {
//                    String tmp=str.substring(begin);
//                    list.add(tmp);
//                }
//            }
//            for (int j = 0; j <list.size() ; j++) {
//                System.out.println(list.get(j));
//            }
//        }
            String str=scanner.nextLine();
            int count=0;
              for (int i = 0; i <str.length() ; i++) {
                   if(str.charAt(i)=='"') {
                       i++;
                      while (i<str.length()&&str.charAt(i)!='"') {
                        i++;
                       }
                   }
                  if (str.charAt(i)==' ') {
                       count++;
                   }
               }
            System.out.println(count+1);
            int flag=1;
            for (int i = 0; i <str.length() ; i++) {
                if(str.charAt(i)=='"') {
                    flag^=1;
                }
                if(str.charAt(i)!=' '&&str.charAt(i)!='"') {
                    System.out.print(str.charAt(i));
                }
                //引号里的空格
                if(str.charAt(i)==' '&&flag==0) {
                    System.out.println(str.charAt(i));
                }
                //单词后的空格换行
                if(str.charAt(i)==' '&&flag==1) {
                    System.out.println();
                }

            }
        }
    }
}
