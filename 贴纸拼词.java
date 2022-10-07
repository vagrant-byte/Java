package 动态规划;

import java.util.HashMap;

public class 贴纸拼词 {
    public int minStickers(String[] stickers,String target) {
        int n=stickers.length;
        int[][] map=new int[n][26];
        for (int i = 0; i <n ; i++) {
            //将每个贴纸的字符转换为数组
            char[] chars=stickers[i].toCharArray();
            for (char c:chars) {
                map[i][c-'a']++;
            }
        }
        HashMap<String,Integer> dp=new HashMap<>();
        dp.put("",0);
        return process(dp,map,target);
    }

    private int process(HashMap<String, Integer> dp, int[][] map, String rest) {

        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        int ans=Integer.MAX_VALUE;//搞定target使用的最少的贴纸数量
        int n=map.length;//n种贴纸
        int[] tmap=new int[26];//tmap替换rest
        char[] target=rest.toCharArray();
        for (char c:target) {
            tmap[c-'a']++;
        }
        for (int i = 0; i <n ; i++) {
            if(map[i][target[0]-'a']==0) {
                continue;
            }
            StringBuilder sb=new StringBuilder();
            //i号贴纸 j枚举a-z
            for (int j = 0; j <26 ; j++) {
                if(tmap[j]>0) {
                    for (int k = 0; k <Math.max(0,tmap[j]-map[i][j]) ; k++) {
                        sb.append((char)('a'+j));
                    }
                }
            }
            String s=sb.toString();
            int tmp=process(dp,map,s);
            if(tmp!=-1) {
                ans=Math.min(ans,1+tmp);
            }
        }
        dp.put(rest,ans==Integer.MAX_VALUE?-1:ans);
        return dp.get(rest);

    }
}
