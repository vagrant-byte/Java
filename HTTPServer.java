package HTTP协议;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {
    //HTTP底层基于TCP实现
    private ServerSocket socket=null;
    //构造方法
    public HTTPServer(int port) throws IOException {
        socket=new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器已启动");
        //线程池 工厂模式 newCachedThreadPool无上限的
        ExecutorService executorService= Executors.newCachedThreadPool();
        while (true) {
            //1.获取链接
            Socket clientSocket=socket.accept();
            //处理链接（短链接方式实现）
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    process(clientSocket);
                }
            });
        }
    }

    private void process(Socket clientSocket){
        //1.读取请求并解析
        try(BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
            //解析首行，三个部分使用空格切分
            String firstLine=bufferedReader.readLine();
            String[] firstLineTokens=firstLine.split(" ");
            String method=firstLineTokens[0];
            String url=firstLineTokens[1];
            String version=firstLineTokens[2];
            //解析header，按行读取 按照冒号空格分割
            Map<String,String> map=new HashMap<>();
            //保存一行
            String line="";
            while ((line=bufferedReader.readLine())!=null&&line.length()!=0) {
                String[] headerTokens=line.split(": ");
                map.put(headerTokens[0],headerTokens[1]);
            }
            //解析body
            //请求完毕打印日志
            System.out.printf("%s %s %s\n",method,url,version);
            for (Map.Entry<String,String> entry:map.entrySet()) {
                System.out.println(entry.getKey()+": "+entry.getValue());
            }
            System.out.println();
            //2.根据请求计数响应
            String resp="";
            if(url.equals("/ok")) {
                bufferedWriter.write(version+" 200 ok\n");
                resp="<h1>hello<h1>";
            } else if(url.equals("/notfound")) {
                bufferedWriter.write(version+" 404 not found\n");
                resp="<h1>not found<h1>";
            } else if(url.equals("/seeother")) {
                //重定向
                bufferedWriter.write(version+" 303 see other\n");
                bufferedWriter.write("Location: http://www.baidu.com\n");
                resp="";
            } else {
                bufferedWriter.write(version+" 200 ok\n");
                resp="<h1>default<h1>";
            }
            //3.把响应写回客户端
            bufferedWriter.write("Content-Type: text/html\n");
            bufferedWriter.write("Content-Length: "+resp.getBytes().length+"\n");
            bufferedWriter.write("\n");
            bufferedWriter.write(resp);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HTTPServer server=new HTTPServer(9090);
        server.start();
    }
}
