import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.FileAlreadyExistsException;

//服务器
public class UdpEchoServer {
    private DatagramSocket socket=null;
    //port表示端口号
   public UdpEchoServer(int port) throws SocketException {
       socket=new DatagramSocket(port);
   }
   //启动服务器
    public void start() throws IOException {
        System.out.println("服务器启动");
        while (true) {
            //1.读取请求 receive  参数DatagramPacket
            //构造DatagramPacket 需要指定一个缓冲区
            DatagramPacket packet=new DatagramPacket(new byte[4080],4080);
            socket.receive(packet);
            String str=new String(packet.getData(),0,packet.getLength());
            //2.根据请求计算相应
            String response=process(str);
            //3.把相应写回客户端 send 需要参数DatagramPacket
            // responsePacket构造时需要指定把这个包发给谁
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,
                                                             packet.getSocketAddress());
            socket.send(responsePacket);
            //4.日志打印
            String log=String.format("[%s:%d] req:%s;resp:%s",
                    packet.getAddress().toString(),
                    packet.getPort(),
                    str,response);
            System.out.println(log);
        }
    }

    private String process(String str) {
       return str;
    }

    public static void main(String[] args) throws IOException {
        UdpEchoServer server=new UdpEchoServer(9090);
        server.start();
    }
}
