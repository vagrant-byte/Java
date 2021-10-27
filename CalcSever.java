import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class CalcSever {
     private DatagramSocket socket=null;

    public CalcSever(int port) throws SocketException {
        socket=new DatagramSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器已启动");
        while (true) {
            //1.读取相应
            DatagramPacket requestPacket=new DatagramPacket(new byte[4080],4080);
            socket.receive(requestPacket);
            String request=new String(requestPacket.getData(),0,requestPacket.getLength());
            //2.计算请求，返回相应
            String response=process(request);
            //把相应写回客户端
            DatagramPacket responsePacket=new DatagramPacket(response.getBytes(),response.getBytes().length,
                                                         requestPacket.getSocketAddress());
            socket.send(responsePacket);
            //日志打印
            String log=String.format("[%s:%d] req:%s,resp:%s",requestPacket.getAddress().toString(),
                                                      requestPacket.getPort(), request,response);
            System.out.println(log);
        }
    }
    //自定义协议 num1;num2;运算符

    private String process(String request) {
        String[] strings=request.split(";");
        if(strings.length!=3) {
            System.out.println("[请求格式错误]");
        }
        int result=0;
        int num1=Integer.parseInt(strings[0]);
        int num2=Integer.parseInt(strings[1]);
        if(strings[2].equals("+")) {
            result=num1+num2;
        } else if(strings[2].equals("-")) {
            result=num1-num2;
        } else if(strings[2].equals("*")) {
            result=num1*num2;
        } else if(strings[2].equals("/")) {
            result=num1/num2;
        } else {
            System.out.println("输入的操作符有误");
        }
        return result+"";
    }

    public static void main(String[] args) throws IOException {
        CalcSever calcSever=new CalcSever(9090);
        calcSever.start();
    }
}
