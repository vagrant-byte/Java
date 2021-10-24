import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

public class UdpDictSever {
    private DatagramSocket socket=null;
    private HashMap<String,String> dict=new HashMap<>();
    public UdpDictSever(int port) throws SocketException {
        socket=new DatagramSocket(port);
        dict.put("hello","你好");
        dict.put("cat","小猫");
        dict.put("dog","小狗");
    }

    private String process(String request) {
        return dict.getOrDefault(request,"单词在词典中不存在");
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        while (true) {
            //1.读取请求并解析
            DatagramPacket packet=new DatagramPacket(new byte[4090],4090);
            socket.receive(packet);
            String request=new String(packet.getData(),0,packet.getLength());
            //2.根据请求计算相应
            String response=process(request);
            //3.把响应写回客户端
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,
                                                             packet.getSocketAddress());
            socket.send(responsePacket);
            //4.日志打印
            String log=String.format("[%s:%d req:%s;resp:%s",responsePacket.getAddress().toString(),responsePacket.getPort(),
                                                                    request,response);
            System.out.println(log);
        }

    }

    public static void main(String[] args) throws IOException {
        UdpDictSever dictSever=new UdpDictSever(9090);
        dictSever.start();
    }


}
