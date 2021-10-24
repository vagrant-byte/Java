import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UdpDictClient {
    private DatagramSocket socket=null;
    private String ServerIP;
    private int ServerPort;

    public UdpDictClient(String severIP, int serverPort) throws SocketException {
        this.ServerIP = severIP;
        this.ServerPort = serverPort;
        this.socket=new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner scanner=new Scanner(System.in);
        while (true) {
            //1.根据用户输入，构造请求
            System.out.print("->");
            String request=scanner.next();
            if(request.equals("exit")) {
                System.out.println("exit");
                return;
            }
            //2.发送请求给服务器
            DatagramPacket requestPacket=new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName(ServerIP),ServerPort);
            socket.send(requestPacket);
            //3.读取服务器相应
            DatagramPacket responsePacket=new DatagramPacket(new byte[4090],4090);
            socket.receive(responsePacket);
            String response=new String(responsePacket.getData(),0,responsePacket.getData().length);
            //4.数据显示给用户
            String log=String.format("req:%s;resp:%s",request,response);
            System.out.println(log);
        }
    }

    public static void main(String[] args) throws IOException {
        UdpDictClient client=new UdpDictClient("127.0.0.1",9090);
        client.start();
    }
}
