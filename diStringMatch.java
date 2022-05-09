public class diStringMatch {
    //增减字符串匹配
    public static int[] diStringMatch(String s) {
        int len=s.length();
        int left=0;
        int right=len;
        int[] res=new int[len+1];
        for (int i = 0; i <s.length(); i++) {
            if(s.charAt(i)=='I') {
                res[i]=left;
                left++;
            } else {
                res[i]=right;
                right--;
            }
        }
        if(s.charAt(len-1)=='I') {
            res[len]=left;
        } else {
            res[len]=right;
        }
        return res;
    }

    public static void main(String[] args) {
        String s="IDID";
        int[] r=diStringMatch(s);
        for (int i = 0; i <r.length ; i++) {
            System.out.println(r[i]);
        }
    }
}
