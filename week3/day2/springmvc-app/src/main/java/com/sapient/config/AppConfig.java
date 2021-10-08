package com.sapient.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.sapient.dao", "com.sapient.web" })
public class AppConfig implements WebApplicationInitializer {

    // this method is automatically called by spring framework whenever the web
    // container (tomcat) is started
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.debug("AppConfig.onStartup() called");
        // create a spring container and pass the config class (this class itself)
        AnnotationConfigWebApplicationContext ctx;
        ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);

        // create a front-controller object (DispatcherServlet)
        // register the spring container with the dispatcher servlet
        Dynamic ds = servletContext.addServlet("ds", new DispatcherServlet(ctx));

        // map / to the dispacher servlet
        ds.addMapping("/"); // equivalent of @WebServlet("/") for DispatcherServlet
        ds.setLoadOnStartup(1);
    }

    // customize view-resolver to resove a view name against view file
    // if view name is "xyz"
    // resovled filname --> /WEB-INF/pages/xyz.jsp
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        irvr.setPrefix("/WEB-INF/pages/");
        irvr.setSuffix(".jsp");
        return irvr;
    }

    @Bean
    public EntityManager em() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TRAINING");
        return emf.createEntityManager();
    }
}
