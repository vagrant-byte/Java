public class projectionArea {
    //三维体投影面积
    //xy 平面的投影面积等于网格上非零数值的数目；
    //yz 平面的投影面积等于网格上每一列最大数值之和；
    //zx 平面的投影面积等于网格上每一行最大数值之和。
    //返回上述三个投影面积之和。
    public int projectionArea(int[][] grid) {
        int xyArea=0;
        int yzArea=0;
        int zxArea=0;
        for (int i = 0; i <grid.length ; i++) {
            int yzHeight=0;
            int zxHeight=0;
            for (int j = 0; j <grid[0].length ; j++) {
                xyArea+=grid[i][j]>0?1:0;
                yzHeight=Math.max(yzHeight,grid[j][i]);
                zxHeight=Math.max(zxHeight,grid[i][j]);
            }
            yzArea+=yzHeight;
            zxArea+=zxHeight;
        }
        return xyArea+yzArea+zxArea;

    }
}
