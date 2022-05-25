public class findSubstringInWraproundString {
    //环绕字符串中唯一的子字符串
    public int findSubstringInWraproundString(String p) {
        //统计以每个字符作为结尾的最长连续序列，他们的和即为所求
        int n=p.length();
        if(n<1) {
            return 0;
        }
        int[] count=new int[26];
        char[] chars=p.toCharArray();
        int maxLength=0;
        for (int i = 0; i <n ; i++) {
            if(i>0&&(chars[i]-chars[i-1]==1||chars[i-1]-chars[i]==25)) {
                maxLength++;
            } else {
                maxLength=1;
            }
            count[chars[i]-'a']=Math.max(maxLength,count[chars[i]-'a']);
        }
        int ret=0;
        for (int i = 0; i <count.length ; i++) {
            ret+=count[i];
        }
        return ret;

    }
}
