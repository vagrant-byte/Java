import java.util.Scanner;

public class Day42 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            char[][] maze=new char[10][10];
            int[][] map=new int[10][10];
            for (int i = 0; i <10 ; i++) {
                String str=scanner.next();
                for (int j = 0; j <10 ; j++) {
                    maze[i][j]=str.charAt(j);
                    map[i][j]=Integer.MIN_VALUE;
                }
            }
            map[0][1]=0;
            dfs(0,1,maze,map);
            System.out.println(map[9][8]);
        }
    }

     static int[][] direction={{0,-1},{-1,0},{0,1},{1,0}};
    private static void dfs(int x, int y, char[][] maze, int[][] map) {
        for (int i = 0; i <4 ; i++) {
            int x1=x+direction[i][0];
            int y1=y+direction[i][1];
            if(x1>=0&&x1<10&&y1>=0&&y1<10&&maze[x1][y]=='.'&&map[x1][y1]>map[x][y]+1) {
                map[x1][y1]=map[x][y]+1;
                dfs(x1,y1,maze,map);
            }
        }
    }

    //解读密码
    public static void main1(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String s=scanner.nextLine();
            StringBuilder stringBuilder=new StringBuilder();
            for (int i = 0; i <s.length() ; i++) {
                if(s.charAt(i)>='0'&&s.charAt(i)<='9') {
                    stringBuilder.append(s.charAt(i));
                }
            }
            System.out.println(stringBuilder.toString());
        }

    }
}
