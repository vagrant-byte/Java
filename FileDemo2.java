import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FileDemo2 {
    //读文件
    public static void main1(String[] args) {
        try(InputStream inputStream=new FileInputStream("d:/test.txt")){
            //按字符集读取
            byte[] bytes=new byte[1024];
            while (true) {
                int len=inputStream.read(bytes);
                //读取完毕退出
                if(len==-1) {
                    break;
                }
                for (int i = 0; i <len ; i++) {
                    System.out.printf("%c",bytes[i]);
                }
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
    //读文件 文件中包含汉字
    public static void main(String[] args) {
        try(InputStream inputStream=new FileInputStream("d:/test.txt")){
            try(Scanner scanner=new Scanner(inputStream)) {
                while (scanner.hasNext()) {
                    String s=scanner.next();
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
