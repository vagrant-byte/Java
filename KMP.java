public class KMP {
    //实现strStr()
    public int strStr(String haystack, String needle) {
        if(haystack==null||needle==null||needle.length()<1||haystack.length()<needle.length()) {
            return -1;
        }
        char[] str1=haystack.toCharArray();
        char[] str2=needle.toCharArray();
        int i1=0;
        int i2=0;
        int[] next=getNextArray(str2);
        while (i1<haystack.length()&&i2<needle.length()) {
            if(str1[i1]==str2[i2]) {
                i1++;
                i2++;
            }else if(next[i2]!=-1) {
                i1++;
            }else {
                i2=next[i2];
            }
        }
        return i2==needle.length()?i1-i2:-1;
    }
    public int[] getNextArray(char[] chars) {
        if(chars.length==1) {
            return new int[]{-1};
        }
        int[] next=new int[chars.length];
        next[0]=-1;
        next[1]=0;
        int i=2;
        int cn=0;
        while (i<next.length) {
            if(chars[i-1]==chars[cn]) {
                next[i++]=++cn;
            }else if(cn>0) {
                cn=next[cn];
            }else {
                next[i++]=0;
            }
        }
        return next;
    }
}
