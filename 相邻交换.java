package 动态规划;

public class 相邻交换 {
    //一个数组中只有两种字符G和B，想让所有的G都放在左侧或所有的G都放在右侧，
    //只能在相邻字符之间进行交换操作，至少需要交换几次
    public int minSteps(String s) {
        if(s==null||s.equals("")) {
            return 0;
        }
        char[] chars=s.toCharArray();
        int gi=0;
        int bi=0;
        int step1=0;
        int step2=0;
        for (int i = 0; i <chars.length ; i++) {
            if(chars[i]=='G') {//当前G在左边
                step1+=i-(gi++);
            }else {//当前B在左边
                step2+=i-(bi++);
            }
        }
        return Math.min(step1,step2);
    }
}
