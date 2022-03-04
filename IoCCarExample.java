import java.io.FileWriter;

public class IoCCarExample {
    public static void main(String[] args) {
        Tire tire=new Tire(15,"红色");
        Bottom bottom=new Bottom(tire);
        Framework framework=new Framework(bottom);
        Car car=new Car(framework);
        car.init();

    }

    static class Car {
        private Framework framework;
        public Car(Framework framework) {
            this.framework=framework;
        }
        public void init() {
            framework.init();
        }
    }

    static class Framework {
        private Bottom bottom;
        public Framework(Bottom bottom) {
            this.bottom=bottom;
        }
        public void init() {
            bottom.init();
        }
    }

    static class Bottom {
        private Tire tire;
        public Bottom(Tire tire) {
            this.tire=tire;
        }
        public void init() {
            tire.init();
        }
    }

    static class Tire {
        private int size;
        private String color;
        public Tire(int size,String color) {
            this.size=size;
            this.color=color;
        }
        public void init() {
            System.out.println("轮胎的大小:"+size);
            System.out.println("轮胎的颜色:"+color);
        }
    }
}
