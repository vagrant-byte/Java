package 在字符串中找字串;

public class BfKMP {
    public static int Bf(String str,String sub) {
        if(str==null||sub==null) {
            return -1;
        }
        int lenStr=str.length();
        int lenSub=sub.length();
        if(lenStr==0||lenSub==0) {
            return -1;
        }
        int i=0;
        int j=0;
        while (i<lenStr&&j<lenSub) {
            if(str.charAt(i)==sub.charAt(j)) {
                i++;
                j++;
            } else {
                i=i-j+1;
                j=0;
            }
        }
        if(j>=lenSub) {
            return i-j;
        } else {
            return -1;
        }
    }
    public static void getNext(String sub,int[] next) {
        next[0]=-1;
        next[1]=0;
        int i=2;
        int k=0;
        for (;i<sub.length();i++) {
            if(k==-1||sub.charAt(i-1)==sub.charAt(k)) {
                next[i]=k+1;
                i++;
                k++;
            } else {
                k=next[k];
            }
        }
    }
    public static int KMP(String str,String sub,int pos) {
        if (str==null||sub==null) return -1;
        int lenStr=str.length();
        int lenSub=sub.length();
        if(lenStr==0||lenSub==0) return -1;
        if(pos<0||pos>=lenStr) return -1;
        int[] next=new int[lenSub];
        getNext(sub,next);
        int i=pos;
        int j=0;
        while (i<lenStr&&j<lenSub) {
            if(j==-1||str.charAt(i)==sub.charAt(j)) {
                i++;
                j++;
            } else {
                j=next[j];
            }
        }
        if(j>=lenSub) {
            return i-j;
        }
        return -1;
    }
    public static void main(String[] args) {
        String str="abababc";
        String sub="abc";
        System.out.println(KMP(str, sub,0));
    }

}
