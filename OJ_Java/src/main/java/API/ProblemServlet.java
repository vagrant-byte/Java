package API;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.Problem;
import dao.ProblemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/problem")
public class ProblemServlet extends HttpServlet {
    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json;charset=utf8");
        ProblemDao problemDao=new ProblemDao();
        String idString=req.getParameter("id");
        if(idString==null||idString.equals("")) {
            //获取题目列表页
            List<Problem> list=problemDao.selectAll();
            String respString=objectMapper.writeValueAsString(list);
            resp.getWriter().write(respString);
        } else {
            //获取题目详情页
            Problem problem=problemDao.selectOne(Integer.parseInt(idString));
            String respString=objectMapper.writeValueAsString(problem);
            resp.getWriter().write(respString);
        }
    }
}
