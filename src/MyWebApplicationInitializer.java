import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletException;

/**
 * MyWebApplicationInitializer
 *
 * @author Mr Li
 * @date 2018/12/28
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
        ac.register();
        ac.refresh();
    }
}
