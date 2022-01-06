package HTTP协议;

import com.sun.corba.se.spi.activation.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServerV3 {
    static class User {
        public String userName;
        public int age;
        public String school;
    }
    private ServerSocket serverSocket=null;
    //session会话 同一个用户的一组访问操作
    private HashMap<String,User> sessions=new HashMap<>();

    private HTTPServerV3(int port) throws IOException {
        serverSocket=new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        ExecutorService executorService= Executors.newCachedThreadPool();
        while (true) {
            Socket clientSocket= serverSocket.accept();
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
            HttpRequest1 request1=HttpRequest1.build(clientSocket.getInputStream());
            HTTPResponse1 response1=HTTPResponse1.buils(clientSocket.getOutputStream());
            //根据请求计算响应
            if(request1.getMethod().equals("GET")) {
                doGet(request1,response1);
            } else if(request1.getMethod().equals("POST")) {
                doPost(request1,response1);
            } else {
                response1.setStatus(405);
                response1.setMessage("Method Not Allowed");
            }
            response1.flush();
        } catch (IOException |NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doPost(HttpRequest1 request1, HTTPResponse1 response1) {
        if (request1.getUrl().startsWith("/login")) {
            String userName=request1.getParameters("username");
            String password=request1.getParameters("password");
            //进行验证
            if(userName.equals("myl")&&password.equals("123")) {
                //登录成功
                response1.setStatus(200);
                response1.setMessage("Ok");
                response1.setHeaders("Content-type","text/html; charset=utf-8");
                //对于登录成功给用户分配一个session key是随机的 values是用户的身份信息
                //身份信息保留在服务器中，不会泄漏
                //给浏览器的cookie只需要包含sessionId即可
                String sessionId= UUID.randomUUID().toString();
                User user=new User();
                user.userName="myl";
                user.age=20;
                user.school="西财行知";
                sessions.put(sessionId,user);
                response1.setHeaders("Set-Cookie","sessionId="+sessionId);
                response1.setBody("<html>");
                response1.setBody("<div>欢迎您! " + userName + "</div>");
                response1.setBody("</html>");
            } else {
                //登录失败
                response1.setStatus(403);
                response1.setMessage("Forbidden");
                response1.setHeaders("Content-Type", "text/html; charset=utf-8");
                response1.setBody("<html>");
                response1.setBody("<div>登陆失败</div>");
                response1.setBody("</html>");
            }
        }
    }

    private void doGet(HttpRequest1 request1, HTTPResponse1 response1) throws IOException {
        //能够支持返回一个html文件
        if(request1.getUrl().startsWith("/index.html")) {
            String sessionId=request1.getCookies("sessionId");
            User user=sessions.get(sessionId);
            if(sessionId==null||user==null) {
                //第一次登录 让代码读取一个index.html这样的文件
                //要想读文件首先先要知道文件路径。把文件内容写入到响应的body中
                response1.setStatus(200);
                response1.setMessage("Ok");
                response1.setHeaders("Content-type","text/html; charset=utf-8");
                InputStream inputStream=HTTPServerV3.class.getClassLoader().getResourceAsStream("index.html");
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line=null;
                while ((line=bufferedReader.readLine())!=null) {
                    response1.setBody(line+"\n");
                }
                bufferedReader.close();
            } else {
                //用户已经登录不需要登录
                response1.setStatus(200);
                response1.setMessage("Ok");
                response1.setHeaders("Content-Type","text/html; charset=utf-8");
                response1.setBody("<html>");
                response1.setBody("<div>" + "您已经登陆了! 无需再次登陆! 用户名: " + user.userName + "</div>");
                response1.setBody(+ user.age + "</div>");
                response1.setBody("<div>" + user.school + "</div>");
                response1.setBody("</html>");

            }
        }
    }

    public static void main(String[] args) throws IOException {
        HTTPServerV3 httpServerV3=new HTTPServerV3(9090);
        httpServerV3.start();
    }
}
