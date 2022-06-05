import java.util.Random;

//在圆内随机生成点
public class Solution2 {
    Random random=new Random();
    public double x;
    public double y;
    public double radius;
    public Solution2(double radius, double x_center, double y_center) {
        this.x=x_center;
        this.y=y_center;
        this.radius=radius;
    }

    public double[] randPoint() {
        double[] res=new double[2];
        while (true) {
            double dx=random.nextDouble()*2*radius-radius;
            double dy=random.nextDouble()*2*radius-radius;
            if(dx*dx+dy*dy<=radius*radius) {
               return new double[]{dx+x,dy+y};
            }
        }
    }
}
