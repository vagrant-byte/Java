package HTTP协议;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class HTTPResponse1 {
    private String version="HTTP/1.1";
    private int status;
    private String message;
    private Map<String,String> headers=new HashMap<>();
    private StringBuilder body=new StringBuilder();
    private OutputStream outputStream=null;

    public static HTTPResponse1 buils(OutputStream outputStream){
        HTTPResponse1 response1=new HTTPResponse1();
        response1.outputStream=outputStream;
        return response1;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHeaders(String key,String val) {
        headers.put(key,val);
    }

    public void setBody(String body) {
        this.body.append(body);
    }

   public void flush() throws IOException {
       BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
       bufferedWriter.write(version+" "+status+" "+message+"\n");
       headers.put("Content-Length",body.toString().getBytes().length+"");
       for (Map.Entry<String,String> entry:headers.entrySet()) {
           bufferedWriter.write(entry.getKey()+": "+entry.getValue());
       }
       bufferedWriter.write("\n");
       bufferedWriter.write(body.toString());
       bufferedWriter.flush();
   }
}
