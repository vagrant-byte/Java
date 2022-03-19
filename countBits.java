public class countBits {
    public int[] countBits(int n) {
        int[] res=new int[n+1];
        for (int i = 0; i <=n ; i++) {
            res[i]=Bits(i);
        }
        return res;
    }

    private int Bits(int i) {
        int count=0;
        while (i!=0) {
            if(i%2==1) {
                count++;
            }
            i/=2;
        }
        return count;
    }
}
