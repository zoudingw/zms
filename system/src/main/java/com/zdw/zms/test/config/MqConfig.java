package com.zdw.zms.test.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/23
 * Description:
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
@Configuration
public class MqConfig {

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/23
     * @description : direct 模式，只需要创建queue,发送消息的routkey与队列名相同即可
      */
    @Bean
    public Queue queue(){
        return new Queue("test");
    }
}
