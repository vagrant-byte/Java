package 数组;

public class 顺时针打印矩阵 {
    public static int[] spiralOrder(int[][] matrix) {
        int n=matrix.length;
        if(n==0) {
            return new int[]{};
        }
        int m=matrix[0].length;
        int[] res=new int[n*m];
        if(n==1){
            for (int i = 0; i <m ; i++) {
                res[i]=matrix[0][i];
            }
            return res;
        }
        if(m==1) {
            for (int i = 0; i <n ; i++) {
                res[i]=matrix[i][0];
            }
            return res;
        }
        int index=0;
        int x=0;
        int y=m-1;
        int z=n-1;
        int l=0;
        while (y-l>=0&&z-x>=0) {
            for (int i=x; i<=y ; i++) {
                res[index++]=matrix[x][i];
            }
            for (int i = x+1; i <=z ; i++) {
                res[index++]=matrix[i][y];
            }
            if(index==res.length&&n%2==1) {
                return res;
            }
            for (int i = y-1; i >=l ; i--) {
                res[index++]=matrix[z][i];
            }
            if(z-x==1&&n%2==0) {
                return res;
            }
            for (int i = z-1; i >x ; i--) {
                res[index++]=matrix[i][l];
            }
            x++;
            y--;
            z--;
            l++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] r={{1,2},{3,4},{5,6}};
        int[] a=spiralOrder(r);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+"  ");
        }
    }
}
