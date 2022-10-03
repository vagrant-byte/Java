package 动态规划.从左往右尝试模型;

import org.omg.CORBA.MARSHAL;

public class process1 {
    //规定1和A对应，2和B对应，3和C对应
    //那么一个数字字符串比如“111”就可以转化为：“AAA”，“KA”，“AK”
    //给定一个只有数字字符串组成的字符串str，返回有多少种转化结果
    public int number(String str) {
        if(str==null||str.length()==0) {
            return 0;
        }
        return process(str.toCharArray(),0);
    }

    private int process(char[] str, int i) {
        if(i==str.length) {
            return 1;
        }
        //i没有到终止位置
        if(str[i]=='0') {
            return 0;
        }
        if(str[i]=='1') {
            int res=process(str,i+1);//i自己作为单独的部分后续有多少中方法
            if(i+1<str.length) {
                res+=process(str,i+2);
            }
            return res;
        }
        if(str[i]=='2') {
            int res=process(str,i+1);
            if(i+1<str.length&&str[i+1]>='0'&&str[i+1]<=6) {
                res+=process(str,i+2);
            }
            return res;
        }
        //str[i]='3'-'9'
        return process(str,i+1);
    }
    //给定两个长度都是N的数组weights和values，weights[i]和values[i]分别代表i号物品的重量和价值
    //给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量
    //返回你能装下最多的价值是多少
    public int getMaxValue(int[] w,int[] v,int bag) {
        return process2(w,v,0,0,bag);
    }

    private int process2(int[] w, int[] v,int index, int alreadyW, int bag) {
        if(alreadyW>bag) {
            return -1;
        }
        if(alreadyW==bag) {
            return 0;
        }
        int p1=process2(w,v,index+1,alreadyW,bag);//不要当前货物后边的价值
        int p2Next=process2(w,v,index+1,alreadyW+w[index],bag);//要当前货物后边的价值
        int p2=-1;
        if(p2Next!=-1) {
            p2=v[index]+p2Next;
        }
        return Math.max(p1,p2);
    }

}
