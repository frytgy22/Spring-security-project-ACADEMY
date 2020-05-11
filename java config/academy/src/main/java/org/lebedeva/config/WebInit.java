package org.lebedeva.config;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.File;
import java.util.EnumSet;

public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        context.setServletContext(servletContext);

        servletContext.setInitParameter("spring.profiles.active", "dev");

        servletContext.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcherServlet", new DispatcherServlet(context));

        File tempDir = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        dispatcherServlet.setMultipartConfig(new MultipartConfigElement(tempDir.getAbsolutePath()));

        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        ServletRegistration.Dynamic h2console = servletContext.addServlet("h2console", WebServlet.class);
        h2console.addMapping("/h2console/*");

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter",
                new CharacterEncodingFilter("UTF-8", true, true));
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);

        encodingFilter.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
    }
}
