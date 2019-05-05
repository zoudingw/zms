package com.zdw.zms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName CharsetConfig
 * @Description TODO
 * @Author zoudingwei
 * @Date 2019/5/4 10:48 AM
 * @Version 1.0
 **/
//@Configuration
public class CharsetConfig implements WebMvcConfigurer {


    @Bean
    public HttpMessageConverter<String> responseBodyCovert(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("utf-8"));
        return  converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyCovert());
    }

}
