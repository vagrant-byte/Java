public class 岛屿数量 {
    public int numIslands(char[][] grid) {
        if(grid==null) {
            return 0;
        }
        int res=0;
        int m=grid.length;
        int n=grid[0].length;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if(grid[i][j]=='1') {
                    res++;
                    infect(grid,i,j,m,n);
                }
            }
        }
        return res;
    }

    private void infect(char[][] grid, int i, int j, int m, int n) {
        if(i<0||i>=m||j<0||j>=n||grid[i][j]!='1') {
            return;
        }
        grid[i][j]='2';
        infect(grid,i+1,j,m,n);
        infect(grid,i-1,j,m,n);
        infect(grid,i,j+1,m,n);
        infect(grid,i,j-1,m,n);
    }
}
