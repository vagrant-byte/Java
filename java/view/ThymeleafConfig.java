package view;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ThymeleafConfig implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context=servletContextEvent.getServletContext();
        //1.创建TemplateEngine实例,负责模板渲染
        TemplateEngine engine=new TemplateEngine();
        //2.创建resolver实例，负责加载模板文件
        ServletContextTemplateResolver resolver=new ServletContextTemplateResolver(context);
        resolver.setPrefix("/WEB-INF/template/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        //3.把resolver对象和engine对象关联起来
        engine.setTemplateResolver(resolver);
        //3.把创建好的engine对象放到ServletContext中
        context.setAttribute("engine",engine);
        System.out.println("初始化 TemplateEngine成功");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
