import java.util.ArrayList;
import java.util.List;

public class selfDiv {
    //自除数
    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list=new ArrayList<>();
        for (int i = left; i <=right ; i++) {
            if(divNum(i)) {
                list.add(i);
            }
        }
        return list;

    }

    public static boolean divNum(int i) {
        int num=i;
        if(i<10) {
            return true;
        } else {
            while (i>0) {
                int tmp=i%10;
                if(tmp==0||num%tmp!=0) {
                    return false;
                }
                i/=10;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        List<Integer> list1=new ArrayList<>();
        list1=selfDividingNumbers(47,85);
        for (int i = 0; i <list1.size() ; i++) {
            System.out.println(list1.get(i));
        }
    }
}
