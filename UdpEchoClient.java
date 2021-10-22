import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpEchoClient {
    private DatagramSocket socket=null;
    private String serverIP;
    private int serverPort;

    public UdpEchoClient(String serverIP, int serverPort) throws SocketException {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.socket=new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            //1.从标准输入读一个数据
            System.out.print("->");
            String request=scanner.nextLine();
            //输入exit退出
            if(request.equals("exit")) {
                System.out.println("exit");
                return;
            }
            //2.把字符构造UDP请求并发送数据
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                                                           InetAddress.getByName(serverIP),serverPort);
            socket.send(requestPacket);
            //3.从服务器读取相应
            DatagramPacket responsePacket=new DatagramPacket(new byte[4080],4080);
            socket.receive(responsePacket);
            String response=new String(requestPacket.getData(),0,requestPacket.getLength());
            //4.显示结果
            String log=String.format("req:%s;resp:%s",request,response);
            System.out.println(log);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpEchoClient client=new UdpEchoClient("127.0.0.1",9090);
        client.start();
    }
}
