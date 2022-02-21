package compile;

import common.FileUtil;

import java.io.File;
import java.util.UUID;

public class Task {
    private static String WORK_DIR=null;
    private static String CLASS=null;
    private static String CODE=null;
    private static String COMPILE_ERROR=null;
    private static String STDOUT=null;
    private static String STDERR=null;

    public Task() {
        WORK_DIR="./tmp/"+ UUID.randomUUID().toString()+"/";
        CLASS="Solution";
        CODE=WORK_DIR+"Solution.java";
        COMPILE_ERROR=WORK_DIR+"compile_error.txt";
        STDOUT=WORK_DIR+"stdout.txt";
        STDERR=WORK_DIR+"stderr.txt";
    }

    public  Answer compileAndRun(Question question) {
        Answer answer=new Answer();
        //创建目录
        File workDir=new File(WORK_DIR);
        if(!workDir.exists()) {
            workDir.mkdirs();
        }
        //把question中的code写入到CODE中
        FileUtil.writeFile(CODE,question.getCode());
        //创建子进程编译javac
        String compileCmd=String.format("javac -encoding utf8 %s -d %s",CODE,WORK_DIR);
        CommandUtil.run(compileCmd,null,COMPILE_ERROR);
        String compileError=FileUtil.readFile(COMPILE_ERROR);
        if(!compileError.equals("")) {
            System.out.println("编译出错");
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }
        //创建子进程调用java命令
        String runCmd=String.format("java -classpath %s %s",WORK_DIR,CLASS);
        CommandUtil.run(runCmd,STDOUT,STDERR);
        String runErr=FileUtil.readFile(STDERR);
        if(!runErr.equals("")) {
            System.out.println("运行出错");
            answer.setError(2);
            answer.setReason(runErr);
            return answer;
        }
        //编译运行都通过
        answer.setError(0);
        answer.setStdout(FileUtil.readFile(STDOUT));
        return answer;
    }
    public static void main(String[] args) {
        Task task=new Task();
        Question question=new Question();
        question.setCode("public class Solution {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"hello world\");\n" +
                "    }\n" +
                "    \n" +
                "}");
        Answer answer=task.compileAndRun(question);
        System.out.println(answer);
    }
}
