public class LeetCode {
    //重新排序得到2的幂
    public boolean reorderedPowerOf2(int n) {
        String s=countDigits(n);
        for(int i=1;i<=1e9;i<<=1){
            if(countDigits(i).equals(s)){
                return true;
            }
        }
        return false;
    }
    public String countDigits(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            ++cnt[n % 10];
            n /= 10;
        }
        return new String(cnt);
    }
}
