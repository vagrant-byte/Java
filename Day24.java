import java.util.ArrayList;
import java.util.Scanner;

public class Day24 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            int row=scanner.nextInt();
            int col=scanner.nextInt();
            int[][] check=new int[row][col];
            for (int i = 0; i <row ; i++) {
                for (int j = 0; j <col ; j++) {
                    check[i][j]=scanner.nextInt();
                }
            }
            ArrayList<Integer> list=new ArrayList<>();
        }
    }

    //年终奖
    public int getMost(int[][] board) {
        // write code here
        for (int i = 1; i <board.length; i++) {
            board[i][0]+=board[i-1][0];
        }
        for (int i = 1; i <board[0].length ; i++) {
            board[0][i]+=board[0][i-1];
        }
        for (int i = 1; i <board.length ; i++) {
            for (int j = 1; j <board[0].length ; j++) {
                board[i][j]=board[i][j]+Math.max(board[i-1][j],board[i][j-1]);
            }
        }
        return board[board.length-1][board[0].length-1];
    }
}
