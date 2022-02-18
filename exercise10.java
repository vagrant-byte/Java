import java.util.HashMap;

public class exercise10 {
    //单词频率
    static HashMap<String,Integer> map=null;
    public exercise10(String[] book) {
        map=new HashMap<>();
        for (String s:book) {
            if(map.containsKey(s)) {
                map.put(s,map.get(s)+1);
            } else {
                map.put(s,1);
            }
        }

    }
    public int get(String word) {
        return map.get(word)==null?0:map.get(word);
    }

    //最大数值
    public int maximum(int a, int b) {
        //return a>b?a:b;
        long s=(long)a-(long)b;
        int k= (int)(s>>>63);
        return a*(k^1)+b*k;

    }
}
