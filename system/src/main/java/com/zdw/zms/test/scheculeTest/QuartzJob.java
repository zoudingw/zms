package com.zdw.zms.test.scheculeTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/24
 * Description:定时任务执行的job
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
@Component(value = "firstJob")
public class QuartzJob {

    Logger logger = LoggerFactory.getLogger(QuartzJob.class);
    /*
     * @author zoudw
     * @param []
     * @return void
     * @date 2019/5/24
     * @description :定时任务执行的方法
      */
    public void jobRun(){
        logger.info("===================quartz job run================");
    }
}
