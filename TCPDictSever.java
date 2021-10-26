import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class TCPDictSever {
    private ServerSocket listenSocket=null;
    private HashMap<String,String> dict=new HashMap<>();
    public TCPDictSever(int port) throws IOException {
        listenSocket=new ServerSocket(port);
        dict.put("hello","你好");
        dict.put("cat","小猫");
        dict.put("dog","小狗");
    }

    public void start() throws IOException {
        System.out.println("服务器已启动");
        while (true) {
            Socket clientSocket=listenSocket.accept();
            processConnection(clientSocket);

        }
    }

    private void processConnection(Socket clientSocket) throws IOException {
        String log=String.format("[%s:%d] 客户端上线",
                clientSocket.getInetAddress().toString(),clientSocket.getPort());
        System.out.println(log);
        try(InputStream inputStream=clientSocket.getInputStream();
            OutputStream outputStream=clientSocket.getOutputStream()) {
            while (true) {
                Scanner scanner=new Scanner(inputStream);
                if(!scanner.hasNext()) {
                    log=String.format("[%s:%d] 客户端下线",
                            clientSocket.getInetAddress().toString(),clientSocket.getPort());
                    System.out.println(log);
                    break;
                }
                //1.读取请求
                String request=scanner.next();
                //2.根据请求计算相应
                String response=process(request);
                //3.把相应写回客户端
                PrintWriter writer=new PrintWriter(outputStream);
                writer.println(response);
                writer.flush();

                log=String.format("[%s:%d] req: %s; resp: %s",
                        clientSocket.getInetAddress().toString(), clientSocket.getPort(),
                        request, response);
                System.out.println(log);
            }

        } catch (IOException e) {
            e.fillInStackTrace();
        } finally {
            clientSocket.close();
        }

    }

    private String process(String request) {
        return dict.getOrDefault(request,"单词在词典中不存在");
    }

    public static void main(String[] args) throws IOException {
        TCPDictSever dictSever=new TCPDictSever(9090);
        dictSever.start();
    }
}
