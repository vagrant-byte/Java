public class missingRolls {
    //找出缺失的观测数据
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int size=rolls.length;
        int sum=(n+size)*mean;
        for (int i = 0; i <rolls.length ; i++) {
            sum-=rolls[i];
        }
        if(sum>n*6||sum<n*1) {
            return new int[]{};
        }
        int[] res=new int[n];
        //以1为单位循环分配
        int index=0;
        while (sum>0) {
            res[index%n]++;
            sum--;
            index++;
        }
        return res;
    }
}
