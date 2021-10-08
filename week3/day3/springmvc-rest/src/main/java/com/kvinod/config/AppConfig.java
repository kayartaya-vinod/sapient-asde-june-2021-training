package com.kvinod.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.kvinod.web" })
public class AppConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx;
        ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        Dynamic ds = servletContext.addServlet("ds", new DispatcherServlet(ctx));
        ds.addMapping("/");
        ds.setLoadOnStartup(1);
    }
}
