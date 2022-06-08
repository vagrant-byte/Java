//有效的回旋镖
public class isBoomerang {
    public boolean isBoomerang(int[][] points) {
        int x1=points[0][0];
        int x2=points[1][0];
        int x3=points[2][0];
        int y1=points[0][1];
        int y2=points[1][1];
        int y3=points[2][1];
        //计算三角形面积不为0 1/2*[(x1y2-x2y1)+(x2y3-x3y2)+(x3y1-x1y3)]
        return (x1*y2-x2*y1)+(x2*y3-x3*y2)+(x3*y1-x1*y3)!=0;
    }
}
