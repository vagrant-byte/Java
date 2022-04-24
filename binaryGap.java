public class binaryGap {
    //二进制间距
    public int binaryGap(int n) {
        int last=-1;
        int ans=0;
        for (int i = 0; n!=0 ; i++) {
            if((n&1)==1) {
                if(last!=-1) {
                    ans=Math.max(ans,i-last);
                }
                last=i;
            }
            n>>=1;
        }
        return ans;
//        int res=0;
//        int count=-1;
//        while (n>0) {
//            if((n&1)==1||count>-1) {
//                count++;
//            }
//            if((n&1)==1&&count>-1) {
//                res=Math.max(res,count);
//                count=0;
//            }
//            n/=2;
//        }
//        return res;
    }
}
