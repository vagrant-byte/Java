import java.util.ArrayList;
import java.util.List;

public class wordBreak {
    //单词拆分
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for (int i = 1; i <=s.length() ; i++) {
            for (int j = 0; j <i ; j++) {
                if(dp[j]&&wordDict.contains(s.substring(j,i))) {
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[s.length()];
//        while (s!=""&&!s.equals("")) {
//            int i = 0;
//            boolean flag=true;
//            for (; i <wordDict.size() ; i++) {
//                if(s.contains(wordDict.get(i))) {
//                    flag=false;
//                    int index=s.indexOf(wordDict.get(i).charAt(0));
//                    String s1=s.substring(0,index);
//                    String s2=s.substring(index+wordDict.get(i).length());
//                    s=s1+s2;
//                }
//            }
//            if(flag) {
//                return false;
//            }
//        }
//        return true;
    }

    public static void main(String[] args) {
        String s="leetcode";
        List<String> list=new ArrayList<>();
        list.add("leet");
        list.add("code");
        boolean flag=wordBreak(s, list);
        System.out.println(flag);
    }
}
