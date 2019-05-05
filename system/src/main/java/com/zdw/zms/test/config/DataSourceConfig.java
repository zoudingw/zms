package com.zdw.zms.test.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description 多数据源配置
 * @Author zoudingwei
 * @Date 2019/5/5 9:07 PM
 * @Version 1.0
 **/
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.one")
    DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

}
