package 数组;

public class 矩阵中的路径 {
    public boolean exist(char[][] board, String word) {
        int m=board.length;
        int n=board[0].length;
        if(board==null||m==0||n==0) {
            return false;
        }
        int len=word.length();
        if(len>m*n) {
            return false;
        }
        boolean[][] visited=new boolean[m][n];
        char[] chars=word.toCharArray();
        for (int i = 0; i <m; i++) {
            for (int j = 0; j <n ; j++) {
                if(dfs(board,chars,visited,i,j,0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] chars, boolean[][] visited, int i, int j, int index) {
        if(index==chars.length-1) {
            return true;
        }
        if(i<0||i>=board.length||j<0||j>=board[0].length||chars[index]!=board[i][j]||visited[i][j]) {
            return false;
        }
        //visited=true 表明访问过了
        visited[i][j]=true;
        boolean res=dfs(board,chars,visited,i+1,j,index+1)||
                    dfs(board,chars,visited,i-1,j,index+1)||
                    dfs(board,chars,visited,i,j+1,index+1)||
                    dfs(board,chars,visited,i,j-1,index+1);
        //在dfs过程中，如果某条路已经不通了需要回溯，将visited[i][j]重新赋值为false
        visited[i][j]=false;
        return res;
    }
}
