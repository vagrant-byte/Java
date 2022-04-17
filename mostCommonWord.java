import java.util.HashMap;

public class mostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] par = paragraph.replace("."," ").replace("!"," ").replace("?"," ").replace("'"," ").replace(";"," ").replace(","," ").toLowerCase().split(" ");
        HashMap<String,Integer> map=new HashMap<>();
        String ans=null;
        int max=0;
        for (int i=0;i<par.length;i++) {
            boolean flag=false;
            for (int j=0;j<banned.length;j++) {
                if(par[i].equals(banned[j])) {
                    flag=true;
                    break;
                }
            }
            if(!flag) {
                map.put(par[i],map.getOrDefault(par[i],0)+1);
                if(!par[i].equals("")&&map.get(par[i])>max) {
                    max=map.get(par[i]);
                    ans=par[i];
                }
            }
        }
        return ans;

    }
}
