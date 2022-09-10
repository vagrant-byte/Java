public class manacher {
    public char[] manacherString(String str) {
        char[] charArr=str.toCharArray();
        char[] res=new char[2*charArr.length+1];
        int index=0;
        for (int i = 0; i <res.length ; i++) {
            res[i]=(i&1)==0?'#':charArr[index++];
        }
        return res;
    }
    public int maxLcpsLength(String s) {
        if (s==null||s.length()==0) {
            return 0;
        }
        char[] str=manacherString(s);
        int[] pArr=new int[str.length];//回文半径的数组
        int C=-1;//中心
        int R=-1;//回文的右边界
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <str.length ; i++) {
            pArr[i]=R>i?Math.min(pArr[2*C-1],R-i):1;
            while (i+pArr[i]<str.length&&i-pArr[i]>-1) {
                if(str[i+pArr[i]]==str[i-pArr[i]]) {
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if(i+pArr[i]>R) {
                R=i+pArr[i];
                C=i;
            }
            max=Math.max(max,pArr[i]);
        }
        return max-1;
    }
    //最长回文串
    public static String longestPalindrome(String s) {
        if(s.length()==1) {
            return s;
        }
        char[] chars=s.toCharArray();
        char[] res=new char[s.length()*2+1];
        int index=0;
        for (int i = 0; i <res.length ; i++) {
            //给数组中加上标识符
            res[i]=(i&1)==0?'#':chars[index++];
        }
        int R=-1;
        int c=-1;
        for (int i = 0; i <res.length ; i++) {
            int left=i-1;
            int right=i+1;
            while (left>=0&&right<res.length&&res[left]==res[right]) {
                left--;
                right++;
            }
            if(R<right&&right<res.length) {
                R=right;
                c=i;
            }
        }
        int start=2*c-R+1;
        String s1="";
        if(start>=0) {
            for (int i = start; i <R ; i++) {
                if(res[i]!='#') {
                    s1+=res[i];
                }
            }
        }else {
            for (int i = 0; i <R ; i++) {
                if(res[i]!='#') {
                    s1+=res[i];
                }
            }
        }
        return s1;
    }

    public static void main(String[] args) {
        String s="bb";
        String res=longestPalindrome(s);
        System.out.println(res);
    }
}
