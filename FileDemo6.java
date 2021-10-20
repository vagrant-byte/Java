import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDemo6 {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入要查找的目录");
        Scanner scanner=new Scanner(System.in);
        String str=scanner.next();
        File file=new File(str);
        if(!file.isDirectory()) {
            System.out.println("输入的路径不是目录");
            return;
        }
        List<File> list=new ArrayList<>();
        System.out.println("请输入要查找的内容");
        String search=scanner.next();
        getFiles(file,search,list);
        for (File file1:list) {
            System.out.println(file1.getCanonicalPath());
        }
    }

    private static void getFiles(File file, String search, List<File> list) {
        File[] files=file.listFiles();
        if(files==null||files.length==0) {
            return;
        }
        for (File file1:files) {
            if(file1.isDirectory()) {
                getFiles(file1,search,list);
            } else {
                if (isContentContains(file1, search)) {
                    list.add(file1.getAbsoluteFile());
                }
            }
        }
    }

    private static boolean isContentContains(File file1, String search) {
        if(file1.getName().compareTo(search)==0) {
            return true;
        }
        StringBuilder stringBuilder=new StringBuilder();
        try(InputStream inputStream=new FileInputStream(file1)){
            try(Scanner scanner=new Scanner(inputStream,"UTF-8")) {
                while (scanner.hasNext()) {
                    stringBuilder.append(scanner.nextLine());
                    stringBuilder.append("\n");
                }
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return stringBuilder.indexOf(search) !=-1;
    }
}
