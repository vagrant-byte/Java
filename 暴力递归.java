import java.util.List;

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
}
