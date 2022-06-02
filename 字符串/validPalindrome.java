package 字符串;
//最多删除一个字符得到回文
public class validPalindrome {
    public boolean validPalindrome(String s) {
        StringBuilder stringBuilder=new StringBuilder(s);
        int[] i=new int[1];//表示从左到右遇到的第一个不相同的字符
        int[] j=new int[1];//表示从右到左遇到的第一个不相同的字符
        if(isPalindrome(stringBuilder,i,j)) {
            return true;
        } else {
            stringBuilder.deleteCharAt(i[0]);
            if(isPalindrome(stringBuilder,null,null)) {
                return true;
            } else {
                stringBuilder.append(i[0]);
                stringBuilder.deleteCharAt(j[0]);
                if(isPalindrome(stringBuilder,null,null)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private boolean isPalindrome(StringBuilder stringBuilder, int[] start, int[] end) {
        //判断回文
        int i=0;
        int j=stringBuilder.length()-1;
        boolean result=true;
        while (i<=j) {
            if(stringBuilder.charAt(i)==stringBuilder.charAt(j)) {
                i++;
                j--;
            } else {
                result=false;
                break;
            }
        }
        if(start!=null) {
            start[0]=i;
        }
        if(end!=null) {
            end[0]=j;
        }
        return result;
    }
}
