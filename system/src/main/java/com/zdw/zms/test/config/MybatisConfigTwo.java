package com.zdw.zms.test.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @ClassName MybatisConfigOne
 * @Description 创建sessionFactory 与 sessionFactoryTemplate
 * @Author zoudingwei
 * @Date 2019/5/5 9:14 PM
 * @Version 1.0
 **/
@Configuration
@MapperScan(value = "com.zdw.zms.test.daoTwo",sqlSessionFactoryRef = "sqlSessionFactoryBeanTwo")
public class MybatisConfigTwo {

    @Resource(name = "dataSourceTwo")
    DataSource dataSourceTwo;

    @Bean
    SqlSessionFactory sqlSessionFactoryBeanTwo() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceTwo);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath:mappers/test/*.xml"));
        SqlSessionFactory object = factoryBean.getObject();
        return object;
    }


    @Bean
    SqlSessionTemplate sqlSessionTemplateTwo() throws Exception{
        return new SqlSessionTemplate(sqlSessionFactoryBeanTwo());
    }

}
