import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class CalcClient {
    private DatagramSocket socket=null;
    private String calcIP;
    private int calcPort;

    public CalcClient(String calcIP, int calcPort) throws SocketException {
        this.calcIP = calcIP;
        this.calcPort = calcPort;
        socket=new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            //1.构造请求
            System.out.println("请输入操作数num1：");
            int num1=scanner.nextInt();
            System.out.println("请输入操作数num2:");
            int num2=scanner.nextInt();
            System.out.println("请输入运算符（+，-，*，/)");
            String operator=scanner.next();
            //2.读取相应发送给服务器
            String request=num1+";"+num2+";"+operator;
            DatagramPacket packet=new DatagramPacket(request.getBytes(),request.getBytes().length,
                                                       InetAddress.getByName(calcIP),calcPort);
            socket.send(packet);
            //3.读取服务器相应
            DatagramPacket responsePacket=new DatagramPacket(new byte[4080],4080);
            socket.receive(responsePacket);
            String response=new String(responsePacket.getData(),0,responsePacket.getLength());
            //4.显示计算结果
            System.out.println("计算结果为:"+response);
        }
    }

    public static void main(String[] args) throws IOException {
        CalcClient calcClient=new CalcClient("127.0.0.1",9090);
        calcClient.start();
    }
}
