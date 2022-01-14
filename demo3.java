import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class demo3 {
    //商品折扣的最终价格
    public int[] finalPrices(int[] prices) {
        for (int i = 0; i <prices.length ; i++) {
            for (int j = i+1; j <prices.length ; j++) {
                if(prices[i]>=prices[j]) {
                    prices[i]-=prices[j];
                    break;
                }
            }
        }
        return prices;
    }
    //字符串中的第一个唯一字符
    public static int firstUniqChar(String s) {
        char[] chars=s.toCharArray();
        HashMap<Character,Integer> map=new HashMap<>(26);
        for (int i = 0; i <chars.length ; i++) {
            if(map.containsKey(chars[i])) {
                map.put(chars[i],map.get(chars[i])+1);
            } else {
                map.put(chars[i],1);
            }
        }
        for (int i = 0; i <chars.length ; i++) {
            if(map.get(chars[i])==1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s="loveleetcode";
        System.out.println(firstUniqChar(s));
    }
}
