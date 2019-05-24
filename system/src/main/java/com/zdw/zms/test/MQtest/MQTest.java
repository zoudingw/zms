package com.zdw.zms.test.MQtest;

import org.springframework.amqp.core.FanoutExchange;
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

    public void directSendMesage(){
        rabbitTemplate.convertAndSend("test","hello");
    }


    public  void  fanoutSendMessage(){
        rabbitTemplate.convertAndSend("fanoutexchange",null,"fanout hello");
    }

    public void topicSendMessage(){
        rabbitTemplate.convertAndSend("topicExchange","phone.news","phone news");
        rabbitTemplate.convertAndSend("topicExchange","huawei.news","huawei news");
        rabbitTemplate.convertAndSend("topicExchange","apple.news","apple news");
    }

    @RabbitListener(queues = "test")
    public void receiver(String msg){
        System.out.println(msg);
    }

    @RabbitListener(queues = "huawei")
    public void  huaweiReceiveMessage(String msg){
        System.out.println("======================="+msg+"================================");
    }
    @RabbitListener(queues = "apple")
    public void  appleReceiveMessage(String msg){
        System.out.println("======================="+msg+"================================");
    }
    @RabbitListener(queues = "phone")
    public void  phoneReceiveMessage(String msg){
        System.out.println("======================="+msg+"================================");
    }

}
