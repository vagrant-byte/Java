import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
//O(1) 时间插入、删除和获取随机元素
public class RandomizedSet {
    List<Integer> list=new ArrayList<>();
    HashMap<Integer,Integer> map=new HashMap<>();
    Random random=new Random();
    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if(map.containsKey(val)) {
            return false;
        } else {
            list.add(val);
            map.put(val,list.size()-1);
            return true;
        }
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        }
        //保存要删除元素的下标
        int index=map.get(val);
        //获取list最后一个元素的值
        int last=list.get(list.size()-1);
        //将要删除的元素和list最后的元素进行交换
        list.set(index,last);
        //删除list最后的元素
        list.remove(list.size()-1);
        //将原最后下标的元素重新插入map中
        map.put(last,index);
        //map中同时移除val
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
