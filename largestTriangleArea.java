public class largestTriangleArea {
    //最大三角形面积
    public static double largestTriangleArea(int[][] points) {
        /*枚举所有三角形然后计算三角形面积并找出最大的三角形面积
        * 设三角形三个订单的坐标为(x1,y1)(x2,y2)(x3,y3)
        * 三角形面积为|x1y2+x2y3+x3y1-x1y3-x2y1-x3y2|
        * */
        double ret=0.0;
        for (int i = 0; i <points.length ; i++) {
            for (int j = i+1; j <points.length ; j++) {
                for (int k = j+1; k <points.length ; k++) {
                    ret=Math.max(ret,Area(points[i][0],points[i][1],points[j][0],points[j][1],points[k][0],points[k][1]));
                }
            }
        }
        return ret;
    }

    private static double Area(int x1, int y1, int x2, int y2, int x3, int y3) {
        return Math.abs(x1*y2+x2*y3+x3*y1-x1*y3-x2*y1-x3*y2)*0.5;
    }

    public static void main(String[] args) {
        int[][] r={{4,6},{6,5},{3,1}};
        double s=largestTriangleArea(r);
        System.out.println(s);

    }
}
