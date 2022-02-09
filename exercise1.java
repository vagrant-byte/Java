public class exercise1 {
    //验证回文串
    public static boolean isPalindrome(String s) {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)>='a'&&s.charAt(i)<='z') {
                stringBuilder.append(s.charAt(i));
            } else if ((s.charAt(i)>='A'&&s.charAt(i)<='Z')) {
                stringBuilder.append((char)(s.charAt(i)+32));
            } else if(s.charAt(i)>='0'&&s.charAt(i)<='9') {
                stringBuilder.append(s.charAt(i));
            }
        }
        String s1=stringBuilder.toString();
        System.out.println(s1);
        StringBuilder stringBuilder1=new StringBuilder();
        for (int i = s1.length()-1; i >=0 ; i--) {
            stringBuilder1.append(s1.charAt(i));
        }
        System.out.println(stringBuilder1.toString());
        if(s1.equals(stringBuilder1.toString())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s="A man, a plan, a canal : Panama";
        Boolean f=isPalindrome(s);
        System.out.println(f);
    }
    //x的平方根
    public static int mySqrt(int x) {
            if(x==0||x==1) {
                return x;
            }
            int left=0;
            int right=x;
            while(left<=right) {
                int mid=left+(right-left)/2;
                if(mid<x/(mid*1.0)) {
                    left=mid+1;
                } else if(mid>x/(mid*1.0)) {
                    right=mid-1;
                } else {
                    return mid;
                }
            }
            return right;
        }
}
