package compile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//实现完整的编译运行模块
public class CommandUtil {
    public static int run(String cmd,String stdoutFile,String stderrFile)  {
        try {
            //创建子进程
            Process process=Runtime.getRuntime().exec(cmd);
            //获取标准输出
            if(stdoutFile!=null) {
                //打开文件
                InputStream stdoutFrom=process.getInputStream();
                FileOutputStream stdoutTo=new FileOutputStream(stdoutFile);
                while (true) {
                    //循环读取数据
                    int ch=stdoutFrom.read();
                    if(ch==-1) {
                        break;
                    }
                    stdoutTo.write(ch);
                }
                //关闭文件
                stdoutFrom.close();
                stdoutTo.close();
            }
            //获取标准错误
            if(stderrFile!=null) {
                InputStream stderrFrom=process.getErrorStream();
                FileOutputStream stderrTo=new FileOutputStream(stderrFile);
                while (true) {
                    int ch=stderrFrom.read();
                    if(ch==-1) {
                        break;
                    }
                    stderrTo.write(ch);
                }
                stderrFrom.close();
                stderrTo.close();
            }
            //进程等待
            int exitCode= process.waitFor();
            return exitCode;
        } catch (IOException  e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static void main(String[] args) {
        CommandUtil.run("javac","stdout.txt","stderr.txt");
    }
}
