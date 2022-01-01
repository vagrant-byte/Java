package HTTP协议;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServerV2 {
    private ServerSocket serverSocket=null;
    //构造方法
    public HTTPServerV2(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }
    public  void start() throws IOException {
        System.out.println("服务器启动");
        while (true) {
            //获取连接
            Socket clientSocket=serverSocket.accept();
            //线程池工厂模式
            ExecutorService executorService= Executors.newCachedThreadPool();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    process(clientSocket);
                }
            });
        }
    }

    private void process(Socket clientSocket) {
        try {
            //1.读取请求并解析
            HttpRequest request=HttpRequest.build(clientSocket.getInputStream());
            System.out.println("request: "+request);
            HttpResponse response=HttpResponse.build(clientSocket.getOutputStream());
            response.setHeaders("Content-Type","text/html");
            //2.根据请求计算响应
            if(request.getUrl().startsWith("/hello")) {
                response.setStatus(200);
                response.setMessage("OK");
                response.setBody("<h1>hello</h1>");
            } else if(request.getUrl().startsWith("/calc")) {
                String aStr=request.getParameters("a");
                String bStr=request.getParameters("b");
                int a=Integer.parseInt(aStr);
                int b=Integer.parseInt(bStr);
                int res=a+b;
                response.setStatus(200);
                response.setMessage("OK");
                response.setBody("<h1> result= "+res+"</h1>");
            } else if(request.getUrl().startsWith("/cookieUser")) {
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeaders("Set-Cookie","user=myl");
                response.setBody("<h1>set cookieUser</h1>");
            } else if(request.getUrl().startsWith("/cookieTime")) {
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeaders("Set-Cookie","time="+System.currentTimeMillis()/1000);
                response.setBody("<h1>set cookieTime</h1>");
            } else {
                response.setStatus(200);
                response.setMessage("OK");
                response.setBody("<h1>hello</h1>");
            }
            //3.把响应写回客户端
            response.flush();
        } catch (IOException  | NullPointerException e) {
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
        HTTPServerV2 serverV2=new HTTPServerV2(9090);
        serverV2.start();
    }


}
