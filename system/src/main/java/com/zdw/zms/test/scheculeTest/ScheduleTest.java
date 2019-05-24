package com.zdw.zms.test.scheculeTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/24
 * Description:
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
//@Component
public class ScheduleTest {

    Logger logger = LoggerFactory.getLogger(ScheduleTest.class);

    @Scheduled(cron = "2/5 * * * * ?") //每5秒执行一次
    public void scheduleOne() {
        try {
            logger.info("=================scheduleOne start================");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "2/3 * * * * ?") //每3秒执行一次
    public void scheduleTwo(){
        logger.info("=================scheduleTwo start================");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "2/7 * * * * ?") //每3秒执行一次
    public void scheduleThree(){
        logger.info("=================scheduleThree start================");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
