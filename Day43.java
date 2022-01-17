import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Day43 {
    public static void main1(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        ArrayList<ArrayList<Integer>> res=new ArrayList<>();
        for(int i=1;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(i+j==m) {
                    ArrayList<Integer> list=new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }
        for (ArrayList<Integer> l:res) {
            System.out.print(l.get(0)+" ");
            System.out.println(l.get(1));
        }
    }
    public static void main(String[] args) {
        String symbol="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String number="222333444555666777788899991234567890";
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int n=sc.nextInt();
            ArrayList<String> list=new ArrayList<>();
            for (int i = 0; i <n ; i++) {
                String str=sc.nextLine();
                //将“-”替换掉
                str=str.replace("-","");
                String res="";
                for (int j = 0; j <7 ; j++) {
                    res+=number.charAt(symbol.indexOf(str.charAt(j)+""));
                }
                res=res.substring(0,3)+"-"+res.substring(3,7);
                //去掉重复着
                if(!list.contains(res)) {
                    list.add(str);
                }
            }
            Collections.sort(list);
            for (int i = 0; i <list.size() ; i++) {
                System.out.println(list.get(i));
            }
            System.out.println();
        }
    }
}
