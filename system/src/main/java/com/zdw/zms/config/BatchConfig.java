package com.zdw.zms.config;

import com.zdw.zms.entity.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Writer;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/27
 * Description:批处理配置类
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
@Configuration
public class BatchConfig {

    @Resource(name = "dataSource")
    DataSource dataSourceOne;

    @Autowired
    JobBuilderFactory jobBuilderFactory; //job建造工厂，用于生成job

    @Autowired
    StepBuilderFactory stepBuilderFactory;// step 建造工厂，用于生成step

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/27
     * @description :构建批处理job
      */
    @Bean
    public Job messageJob(@Qualifier(value = "messageStep")Step messageStp){
      return   jobBuilderFactory
                .get("messageJob") //messageJob就是job的名字
                .start(messageStp) //传入开始的第一个步骤
                .build();
    }

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/27
     * @description :构建step
      */
    public Step messageStep(@Qualifier(value = "messageReader")FlatFileItemReader<User> messageReader,
                            @Qualifier(value = "messageWriter")JdbcBatchItemWriter<User> messageWiter,
                            @Qualifier(value = "errorWriter")Writer errorWriter){
        return stepBuilderFactory
                .get("messageStep")
                .<User,User>chunk(10) //表示每10条数据进行一次写操作,输入类型为user输出类型也是user
                .reader(messageReader)
                .listener(new MessageItemReaderListener(errorWriter)) //设置监听器
                .writer(messageWiter)
                .listener(new MessageWriterListener(errorWriter))//设置监听器
                .builder;
    }

}
