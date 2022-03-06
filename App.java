import model.UserBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //获取Spring上下文
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-config.xml");
        //从Spring(上下文）中获取到业务对象[通过id来获取]
        //UserBean userBean= (UserBean) context.getBean("userinfo");
        //从Spring(上下文）中获取到业务对象[通过对象类型来获取bean对象]
        //UserBean userBean=context.getBean(UserBean.class);
        //从Spring(上下文）中获取到业务对象[通过id+类型来获取bean对象]
        UserBean userBean=context.getBean("userinfo",UserBean.class);
        userBean.printName("zhangsan");
    }
}
