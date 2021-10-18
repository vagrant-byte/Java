import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FileDemo3 {
    //写文件
    public static void main1(String[] args) {
        try(OutputStream outputStream=new FileOutputStream("d:/test.txt")) {
            //按字符集写文件
           String s="hello world";
           outputStream.write(s.getBytes());

        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
    //写文件 汉字
    public static void main(String[] args) {
        try(OutputStream outputStream=new FileOutputStream("d:/test.txt")) {
            //使用PrintWriter类包装
            try(PrintWriter printWriter=new PrintWriter(outputStream)) {
                printWriter.write("你好世界");
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
