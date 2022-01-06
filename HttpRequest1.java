package HTTP协议;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HttpRequest1 {
    private String method;
    private String url;
    private String version;
    //头部
    private HashMap<String,String> headers=new HashMap<>();
    //url 参数 boby参数
    private HashMap<String,String > parameters=new HashMap<>();
    //cookie参数
    private HashMap<String,String> cookies=new HashMap<>();
    private String body;

    public static HttpRequest1 build(InputStream inputStream) throws IOException {
        HttpRequest1 httpRequest1=new HttpRequest1();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        //解析头部
        String firstLine=bufferedReader.readLine();
        String[] firstLineTokens=firstLine.split(" ");
        httpRequest1.method=firstLineTokens[0];
        httpRequest1.url=firstLineTokens[1];
        httpRequest1.version=firstLineTokens[2];
        //解析url
        int pos=httpRequest1.url.indexOf("?");
        if(pos!=-1) {
            String queryString=httpRequest1.url.substring(pos+1);
            parseKV(queryString,httpRequest1.parameters);
        }
        //解析header部分
        String line="";
        while ((line=bufferedReader.readLine())!=null&&line.length()!=0) {
            String[] headerTokens=line.split(": ");
            httpRequest1.headers.put(headerTokens[0],headerTokens[1]);
        }
        //解析cooker
        String cookie=httpRequest1.headers.get("Cookie");
        if(cookie!=null) {
            parseCookie(cookie,httpRequest1.cookies);
        }
        //解析body
        if(httpRequest1.method.equals("POST")||httpRequest1.method.equals("PUT")) {
            int contentLength=Integer.parseInt(httpRequest1.headers.get("Content-Length"));
            char[] buff=new char[contentLength];
            int len=bufferedReader.read(buff);
            httpRequest1.body=new String(buff,0,len);
            parseKV(httpRequest1.body,httpRequest1.parameters);
        }
        return httpRequest1;
    }

    private static void parseCookie(String cookie, HashMap<String, String> cookies) {
        String[] kvTokens=cookie.split("; ");
        for (String s:kvTokens) {
            String[] kv=s.split("=");
            cookies.put(kv[0],kv[1]);
        }
    }

    private static void parseKV(String queryString, HashMap<String, String> parameters) {
        String[] q=queryString.split("&");
        for (String s:q) {
            String[] strings=s.split("=");
            parameters.put(strings[0],strings[1]);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }

    public String getHeaders(String key) {
        return headers.get(key);
    }

    public String getParameters(String key) {
        return parameters.get(key);
    }

    public String getCookies(String key) {
        return cookies.get(key);
    }

    public String getBody() {
        return body;
    }
}
