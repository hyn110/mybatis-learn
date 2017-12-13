package com.fmi110.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author huangyunning
 *   java配置类 , 取代 xml 配置 WebMvcConfigurerAdapter
 *  类中提供了很多对web设置的方法,重写这些方法对应用进行设定 比如,添加拦截器 , 添加格式转换器
 *  ,消息转换器等,通常可以通过方法名推测或看文档
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.fmi110")
public class MyMVCConfig extends WebMvcConfigurerAdapter {

    private final static Logger log = LoggerFactory.getLogger(MyMVCConfig.class);

    /**
     * 添加路劲映射,通常设置直接的页面跳转用的的
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        log.info("===============addViewControllers==================");

        // 当请求为 /upload 时,返回 upload.jsp 页面
        registry.addViewController("/toupload").setViewName("upload");
        super.addViewControllers(registry);
    }

    /**
     * 配置视图解析对象
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {

        log.info("===============internalResourceViewResolver==================");

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/webapp/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 注入文件解析器,处理文件上传时需要注入该对象
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {

        log.info("===============multipartResolver==================");

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8"); // 默认 iso-8859-1
        commonsMultipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024); // 设置单个文件最大大小
        commonsMultipartResolver.setMaxUploadSize(50 * 1024 * 1024); // 设置最大上传
        return commonsMultipartResolver;
    }
}