package 回文;

public class Palindrome {
    //回文字串
    int num=0;
    public  int countSubstrings1(String s) {
        for (int i = 0; i <s.length() ; i++) {
            count(s,i,i);//回文长度是奇数
            count(s,i,i+1);//回文长度是偶数
        }
        return num;
    }

    private void count(String s, int start, int end) {
        while (start>=0&&end<s.length()&&s.charAt(start)==s.charAt(end)) {
            num++;
            start--;
            end++;
        }
    }

    public int countSubstrings(String s) {
        if(s==null||s.length()<1) {
            return -1;
        }
        int count=0;
        for (int i = 0; i <s.length() ; i++) {
            for (int j = i+1; j <=s.length() ; j++) {
                String text=s.substring(i,j);
                if(isPalindromic(text)) {
                    count++;
                }
            }
        }
        return count+s.length();

    }
    //最长回文字串 暴力
    public String longestPalindrome(String s) {
        if(s==null||s.length()<1) {
            return null;
        }
        if(s.length()==1) {
            return s;
        }
        String ans="";
        int max=0;
        for (int i = 0; i <s.length() ; i++) {
            //此处的等于用于防止越界，并且保证了自己本身是一个回文
            for (int j = i+1; j <=s.length() ; j++) {
                String text=s.substring(i,j);
                //判断是否是回文 是否比当前的回文串长度长
                if(isPalindromic(text)&&text.length()>max) {
                    ans=text;
                    max=Math.max(max,text.length());
                }
            }
        }
        return ans;
    }

    private boolean isPalindromic(String text) {
        for (int i = 0; i <text.length()/2 ; i++) {
            if(text.charAt(i)!=text.charAt(text.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
}
