import java.util.*;

public class 暴力递归 {
    //汉诺塔问题
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        int n=A.size();
        func(n,A,C,B);
    }

    private void func(int n, List<Integer> from, List<Integer> to, List<Integer> other) {
        if(n==1) {
            to.add(from.remove(from.size()-1));//将a中元素移入c中
            return;
        }else {
            func(n-1,from,other,to);
            to.add(from.remove(from.size()-1));
            func(n-1,other,to,from );
        }
    }
    //不同的子序列
    public static ArrayList<ArrayList<Character>> resSum=new ArrayList<>();
    public static int distinctSubseqII(String s) {
        char[] chars=s.toCharArray();
        ArrayList<Character> list=new ArrayList<>();
        process(chars,0,list);
        return resSum.size();
    }
    //当前位置为i，判断要或者不要，两条路
    //res之前的选择所形成的列表
    private static void process(char[] chars, int i, ArrayList<Character> res) {
        if (i == chars.length) {
            resSum.add(res);
            return;
        }
        ArrayList<Character> resKeep = copyList(res);//将列表中的内容拷被
        resKeep.add(chars[i]);//要当前位置
        process(chars, i + 1, resKeep);
        ArrayList<Character> resNoInclude = copyList(res);
        process(chars, i + 1, resNoInclude);//不要当前位置
    }
    private static ArrayList<Character> copyList(ArrayList<Character> res) {
        ArrayList<Character> result=new ArrayList<>();
        for (int i = 0; i <res.size() ; i++) {
            result.add(res.get(i));
        }
        return result;
    }
    
    //全排列问题
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();
        if(nums==null||nums.length==0) {
            return list;
        }
        process1(nums,0,list);
        Set<List<Integer>> set=new HashSet<>();
        for (int i = 0; i <list.size() ; i++) {
            set.add(list.get(i));
        }
        List<List<Integer>> res=new ArrayList<>();
        Iterator<List<Integer>> it=set.iterator();
        while (it.hasNext()) {
            res.add(it.next());
        }
        return res;
    }

    private void process1(int[] nums, int i, List<List<Integer>> list) {
        if(i==nums.length) {
            List<Integer> res=new ArrayList<>();
            for (int j = 0; j <i ; j++) {
                res.add(nums[j]);
            }
            list.add(res);
        }
        for (int j = i; j <nums.length ; j++) {
            swap(nums,i,j);//将i位置和j位置交换，即每个元素都可能在i位置
            process1(nums,i+1,list);
            swap(nums,i,j);//将i和j位置交换是为了不影响元素的初始顺序
        }
    }
    public void swap(int[] a,int i,int j) {
        int tmp=a[i];
        a[i]=a[j];
        a[j]=tmp;
    }


    //预测赢家
    public boolean PredictTheWinner(int[] nums) {
        if(nums==null||nums.length==0) {
            return true;
        }
        //i,j分表表示左右位置
        int sum1=f(nums,0,nums.length-1);//玩家1先手获得的和
        int sum2=s(nums,0,nums.length-1);//玩家2后手获得的和
        return sum1>=sum2?true:false;
    }
    private int f(int[] nums, int i, int j) {
        if(i==j) {
            return nums[i];
        }
        return Math.max(nums[i]+s(nums,i+1,j),nums[j]+s(nums,i,j-1));
    }
    private int s(int[] nums, int i, int j) {
        if(i==j) {
            //i==j时，先手把当前值拿到后手只剩0了
            return 0;
        }
        //先手肯定拿大的，那么后手就找小的
        return Math.min(f(nums,i+1,j),f(nums,i,j-1));
    }


    public static void main(String[] args) {
        String s="abc";
        int n=distinctSubseqII(s);
        System.out.println(n);
    }
}
