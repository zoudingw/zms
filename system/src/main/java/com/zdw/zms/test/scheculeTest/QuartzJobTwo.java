package com.zdw.zms.test.scheculeTest;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/24
 * Description:继承quzrtzJobBean,设置jobdetail
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
public class QuartzJobTwo extends QuartzJobBean {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    Logger logger = LoggerFactory.getLogger(QuartzJobTwo.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("===================quartz job two run and username:{}================",username);
    }
}
