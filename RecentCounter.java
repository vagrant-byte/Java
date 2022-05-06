import java.util.LinkedList;

public class RecentCounter {
    //最近的请求次数
    LinkedList<Integer> list=new LinkedList<>();

    public RecentCounter() {

    }

    public int ping(int t) {
        list.add(t);
        while(list.get(list.size()-1)-list.get(0)>3000) {
            list.remove(0);
        }
        return list.size();

    }
}
