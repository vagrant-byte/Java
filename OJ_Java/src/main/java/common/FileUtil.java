package common;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static String readFile(String filePath){
        StringBuilder result=new StringBuilder();
        try(FileReader fileReader=new FileReader(filePath)) {
            while (true) {
                int ch= fileReader.read();
                if(ch==-1) {
                    break;
                }
                result.append((char)ch);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void writeFile(String filePath,String content) {
        try(FileWriter fileWriter=new FileWriter(filePath)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileUtil.writeFile("/d:/test.txt","hello world");
        String s=FileUtil.readFile("/d:/test.txt");
        System.out.println(s);
    }
}
