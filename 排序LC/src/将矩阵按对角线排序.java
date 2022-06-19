public class 将矩阵按对角线排序 {
    public int[][] diagonalSort(int[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        for (int i = 0; i <Math.min(m,n) ; i++) {
            for (int j = 0; j <m-1 ; j++) {
                for (int k = 0; k <n-1 ; k++) {
                    if(mat[j][k]>mat[j+1][k+1]) {
                        int tmp=mat[j][k];
                        mat[j][k]=mat[j+1][k+1];
                        mat[j+1][k+1]=tmp;
                    }
                }
            }
        }
        return mat;

    }
}
