package 双指针;

public class 判断是否为回文字符串 {
    public boolean judge (String str) {
        // write code here
        int left=0;
        int right=str.length()-1;
        char[] chars=str.toCharArray();
        while (left<right) {
            if(chars[left]!=chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
