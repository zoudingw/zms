package com.zdw.zms.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName FastJsonConfig
 * @Description TODO
 * @Author zoudingwei
 * @Date 2019/5/4 11:41 AM
 * @Version 1.0
 **/
@Configuration
@EnableWebMvc
public class ZmsMvcConfig  implements WebMvcConfigurer {



    /*
     * @Author zoudingwei
     * @Description 把fastjson 设置为转换器
     * @Date 2019/5/4 4:32 PM
     * @Param [converters]
     * @return void
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setCharset(Charset.forName("utf-8"));
        config.setDateFormat("yyy-MM-dd hh:mm:ss");
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }

    /*
     * @Author zoudingwei
     * @Description 一般用来设置静态资源的映射位置
     * @Date 2019/5/4 4:34 PM
     * @Param [registry]
     * @return void
     **/
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")  //访问规则
                .addResourceLocations("classpath:/static/"); //存放路径
               // .addResourceLocations("file://Users//zoudingwei//Downloads/"); //存放路径
    }*/
}
