public class rotate {
    //选择图片
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for (int i = 0; i <=n/2 ; i++) {
            for (int j = i; j <n-i-1 ; j++) {
                int a=matrix[i][j];
                int b=matrix[j][n-i-1];
                int c=matrix[n-i-1][n-1-j];
                int d=matrix[n-j-1][i];
                matrix[i][j]=d;
                matrix[j][n-i-1]=a;
                matrix[n-i-1][n-j-1]=b;
                matrix[n-j-1][i]=c;
            }
        }
    }
}
