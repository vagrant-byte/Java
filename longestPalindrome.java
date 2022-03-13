public class longestPalindrome {
    //最长回文子串
    public String longestPalindrome(String s) {
        String res="";//用来记录最长回文子串
        for (int i = 0; i <s.length() ; i++) {
            String s1=palindrome(s,i,i);
            String s2=palindrome(s,i,i+1);
            res=res.length()>s1.length()?res:s1;
            res=res.length()>s2.length()?res:s2;
        }
        return res;

    }

    private String palindrome(String s, int left, int right) {
        while (left>=0&&right<=s.length()-1) {
            if(s.charAt(left)==s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left+1,right);
    }
}
