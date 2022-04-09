import java.util.HashMap;
import java.util.Map;

//到达终点
public class reachingPoints {
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        //逆向思考 从(tx,ty)推到(sx,sy)只能有一种操作，就是将tx，ty中较大
//        int max=tx>ty?tx:ty;
//        HashMap<Integer,Integer> map=new HashMap<>();
//        if(sx==sy) {
//            map.put(sx,2);
//        } else {
//            map.put(sx,1);
//            map.put(sy,1);
//        }
//        if(sx>sy) {
//            int tmp=sx;
//            sx=sy;
//            sy=tmp;
//        }
//        int num=0;
//        while (num<=max) {
//            num=sx+sy;
//            if(map.containsKey(num)) {
//                map.put(num,map.get(num)+1);
//            } else {
//                map.put(num,1);
//            }
//            sy=sx+sy;
//        }
//        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
//            if(tx==ty) {
//                if(map.containsKey(tx)&&map.get(tx)==2) {
//                    return true;
//                }else {
//                    return false;
//                }
//            }else {
//                if(map.containsKey(tx)&&map.containsKey(ty)) {
//                    return true;
//                }else {
//                    return false;
//                }
//            }
//        }
//        return false;
    }

    public static void main(String[] args) {
        int sx=6;
        int sy=5;
        int tx=11;
        int ty=16;
        boolean x=reachingPoints(sx,sy,tx,ty);
        System.out.println(x);
    }
}
