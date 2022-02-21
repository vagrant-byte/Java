package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.CodeInValidException;
import common.ProblemNotFoundExec;
import compile.Answer;
import compile.Question;
import compile.Task;
import dao.Problem;
import dao.ProblemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@WebServlet("/compile")
public class CompileServlet extends HttpServlet {
    static class CompileRequest {
        public int id;
        public String code;
    }
    static class CompileResponse {
        public int error;
        public String reason;
        public String stdout;
    }
    //获取用户状态
    private ObjectMapper objectMapper=new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompileRequest compileRequest=null;
        CompileResponse compileResponse=new CompileResponse();
        try {
            resp.setStatus(200);
            resp.setContentType("application/json;charset=utf8");
            //1.读取请求并按照json格式解析
            String body=readBody(req);
            compileRequest=objectMapper.readValue(body,CompileRequest.class);
            //2.根据请求中的id找到对应题目的详情
            ProblemDao problemDao=new ProblemDao();
            Problem problem=problemDao.selectOne(compileRequest.id);
            if(problem==null) {
                //找不到对应的题目id
                throw new ProblemNotFoundExec();
            }
            //用户提交的代码
            String requestCode=compileRequest.code;
            //测试用例
            String testCode=problem.getTestCode();
            //3.将用户提交代码和测试用例进行拼接
            String finalCode=mergeCode(requestCode,testCode);
            if(finalCode==null) {
                throw new CodeInValidException();
            }
            //4.创建Task类编译运行代码
            Task task=new Task();
            Question question=new Question();
            question.setCode(finalCode);
            Answer answer=task.compileAndRun(question);
            compileResponse.error=answer.getError();
            compileResponse.reason=answer.getReason();
            compileResponse.stdout=answer.getStdout();
        } catch (ProblemNotFoundExec problemNotFoundExec) {
            compileResponse.error=3;
            compileResponse.reason="题目id不存在id="+compileRequest.id;
        } catch (CodeInValidException e) {
            compileResponse.error=3;
            compileResponse.reason="提交代码不合格";
        } finally {
            String respString=objectMapper.writeValueAsString(compileResponse);
            resp.getWriter().write(respString);
        }
    }

    private String mergeCode(String requestCode, String testCode) {
        int index=requestCode.lastIndexOf("}");
        if(index==-1) {
            return null;
        }
        String subStr=requestCode.substring(0,index);
        return subStr+testCode+"\n}";
    }

    private static String readBody(HttpServletRequest req) throws UnsupportedEncodingException {
        int len=req.getContentLength();
        byte[] bytes=new byte[len];
        try(InputStream inputStream=req.getInputStream()) {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes,"utf8");
    }
}
