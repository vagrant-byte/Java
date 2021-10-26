import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPDictClient {
    private Socket socket=null;
    private String DictIP;
    private int DictPort;

    public TCPDictClient( String dictIP, int dictPort) throws IOException {
        DictIP = dictIP;
        DictPort = dictPort;
        this.socket =new Socket(dictIP, dictPort);
    }

    public void start() {
        Scanner scanner=new Scanner(System.in);
        try(InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream()) {
            while (true) {
                System.out.print("->");
                String request=scanner.next();
                if(request.equals("exit")) {
                    break;
                }
                //读取内容构造请求发送给服务器
                PrintWriter printWriter=new PrintWriter(outputStream);
                printWriter.println(request);
                printWriter.flush();
                //从服务器上读取相应
                Scanner responseSc=new Scanner(inputStream);
                String response=responseSc.next();
                //显示结果到界面
                String log = String.format("req: %s; resp: %s", request, response);
                System.out.println(log);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPDictClient dictClient=new TCPDictClient("127.0.0.1",9090);
        dictClient.start();
    }
}
