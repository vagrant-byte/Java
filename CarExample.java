public class CarExample {
    public static void main(String[] args) {
        //汽车
        Car car=new Car();
        car.run();
    }
    static class Car {
        public void run() {
            //车身
            Framework framework=new Framework();
            framework.init();
        }
    }

    static class Framework {
        public void init() {
            //底盘
            Bottom bottom=new Bottom();
            bottom.init();
        }
    }

    static class Bottom{
        public void init() {
            //轮胎
            Tire tire=new Tire();
            tire.init();
        }
    }

    static class Tire {
        private int size=15;
        public void init() {
            System.out.println("轮胎的大小:"+size);
        }
    }

}
