package com.zdw.zms.test.controller;

import com.zdw.zms.test.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WebSocketTest
 * @Description TODO
 * @Author zoudingwei
 * @Date 2019/5/19 12:41 PM
 * @Version 1.0
 **/
@RestController
public class WebSocketTest {

    @MessageMapping("/hello") //由于已经设置了访问路径前缀/zms。那完整的访问就是/zms/hello
    @SendTo("/topic/greetings")  //把返回的消息放入/topic前缀的broker中，进行广播,该注解是用来广播的
    public Message greeting(Message message) throws Exception{

        return message;
    }



}
