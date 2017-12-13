package com.fmi110.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


/**
 * WebApplicationInitializer 是spring提供用来配置servlet 3.0+ 的接口,
 * 从而取代了 web.xml 的位置. 实现此接口将会自动被 SpringServletContainerInitializer(用来启动servlet3.0容器) 读取
 */
public class WebConfigure implements WebApplicationInitializer {

    private final static Logger log = LoggerFactory.getLogger(WebConfigure.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        log.info("===========onStartup===============");

        // 1 创建上下文
        // 2 注册配置类
        // 3 关联servlet容器上下文
        // 4 servlet容器上下文添加spring前端控制器servlet
        // 5 设置url-pattern 和 启动顺序
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(MyMVCConfig.class);
        appContext.setServletContext(servletContext);

        // 需要传递spring的上下文
        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setLoadOnStartup(1);

    }

}