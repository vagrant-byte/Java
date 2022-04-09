import java.util.HashMap;
import java.util.Map;

//到达终点
public class reachingPoints {
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx>0&&ty>0) {
            if(sx==tx&&sy==ty) {
                return true;
            }
            if(tx>ty) {
                tx-=Math.max((tx-sx)/ty,1)*ty;
            } else {
                ty-=Math.max((ty-sy)/tx,1)*tx;
            }
        }
        return false;
        //逆向思考 从(tx,ty)推到(sx,sy)只能有一种操作，就是将tx，ty中较大值减去较小(因为顺推的时候是(x, y)
        // 可以转换到 (x, x+y) 或者 (x+y, y)，则逆推的时候只能将较大者减去较小者）
        //超时了
//        while (tx>0&&ty>0) {
//            if(sx==tx&&sy==ty) {
//                return true;
//            }
//            if(tx>ty) {
//                tx-=ty;
//            } else {
//                ty-=tx;
//            }
//        }
//        return false;
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
