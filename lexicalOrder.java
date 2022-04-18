import java.util.ArrayList;
import java.util.List;

public class lexicalOrder {
    //字典序排数
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> list=new ArrayList<>();
        int num=1;
        for (int i = 0; i <n ; i++) {
            list.add(num);
            if(num*10<=n) {
                num*=10;
            } else {
                while (num%10==9||num+1>n) {
                    num/=10;
                }
                num++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
       List<Integer> list= lexicalOrder(13);
        for (int i = 0; i <list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
