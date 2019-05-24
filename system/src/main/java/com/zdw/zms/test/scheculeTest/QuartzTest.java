package com.zdw.zms.test.scheculeTest;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

import java.util.concurrent.Executors;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/24
 * Description:配置jobDetail 和trigger触发器,以及schduleFactory,定时器配置三部曲
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
@Configuration
public class QuartzTest {

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description : 设置第一种jobDetail,从spring容器中获取
      */
    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetailFactoryBean(){
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setTargetBeanName("firstJob");
        jobDetailFactoryBean.setTargetMethod("jobRun");
        return jobDetailFactoryBean;
    }

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description :设置第二种jobDetail
      */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBeanTwo(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        //设置QuartzJobBean的子类，就是真正需要执行任务的类
        jobDetailFactoryBean.setJobClass(QuartzJobTwo.class);

        //传递参数使用
        JobDataMap map = new JobDataMap();
        map.put("username","zdw");
        jobDetailFactoryBean.setJobDataMap(map);
        return jobDetailFactoryBean;
    }

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description :设置触发器工厂bean，该触发器工厂产生的触发器触发的任务是有执行次数限制的
      */
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        simpleTriggerFactoryBean.setRepeatCount(3);//任务重复执行的次数
        simpleTriggerFactoryBean.setRepeatInterval(5000);//任务执行的间隔
        simpleTriggerFactoryBean.setStartDelay(2000);
        return  simpleTriggerFactoryBean;
    }

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description :设置第二种触发器工厂bean，该触发器可设置corn表达式
      */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBeanTwo().getObject());
        factoryBean.setCronExpression("2/6 * * * * ?");//设置七子表达式
        return factoryBean;

    }

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description :配置scheduleFactoryBean,加入触发器
      */

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factoryBean =new SchedulerFactoryBean();
        //获取触发器
        SimpleTrigger simpleTrigger = simpleTriggerFactoryBean().getObject();

        CronTrigger cronTrigger = cronTriggerFactoryBean().getObject();

        //设置触发器
        factoryBean.setTriggers(simpleTrigger,cronTrigger);

        //设置线程池大小
        factoryBean.setTaskExecutor(Executors.newScheduledThreadPool(10));
        return factoryBean;
    }
}
