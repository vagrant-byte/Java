public class 玩筹码 {
    public int minCostToMoveChips(int[] position) {
        int count1=0;
        int count2=0;
        for (int i = 0; i <position.length ; i++) {
            if(position[i]%2==0) {
                count1++;
            }
            if(position[i]%2==1) {
                count2++;
            }
        }
        return Math.min(count1,count2);
    }
}
