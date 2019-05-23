package com.zdw.zms.test.MQtest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/23
 * Description:
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
@Component
public class MQTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMesage(){
        rabbitTemplate.convertAndSend("test","hello");
    }

    @RabbitListener(queues = "test")
    public void receiver(String msg){
        System.out.println(msg);
    }
}
