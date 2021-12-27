package udemy.learnspring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Slf4j
public abstract class WellAppinitializer implements WebApplicationInitializer {
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("On Startup");
        log.debug("DEBUGGGGGGG");
        System.out.println("Whats happening?");
        //Create the spring application Context

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        //Create a Dispatcher Servlet
        DispatcherServlet dispatcherServlet =  new DispatcherServlet(context);

        ServletRegistration.Dynamic registration = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");

    }
}

