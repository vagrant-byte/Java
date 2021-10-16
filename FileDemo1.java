import java.io.File;
import java.util.ArrayList;
import java.util.List;

public  class FileDemo1 {
       
    //使用递归获取目录的所有文件
    public static List<String> list=new ArrayList<>();
    public static void getAllFiles(String base) {
        File file=new File(base);
        //判断是目录还是文件
        if(file.isFile()) {
            list.add(base);
            return;
        } else if(file.isDirectory()) {
            String[] strings=file.list();
            for (String s:strings) {
                getAllFiles(base+'/'+s);
            }
        } else {
            //既不是目录也不是文件
        }

    }
    public static void main1(String[] args) {
        getAllFiles(".");
        for (String s:list) {
            System.out.println(s);

        }
    }




}
