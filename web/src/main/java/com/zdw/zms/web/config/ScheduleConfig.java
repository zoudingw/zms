package com.zdw.zms.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/24
 * Description:配置定时器，使用线程池实现多线程并发执行任务,这里是通过注解@SChedule来实现定时任务
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //设置线程池
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(8));
    }
}
