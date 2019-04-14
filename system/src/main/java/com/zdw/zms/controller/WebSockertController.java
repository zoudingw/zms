package com.zdw.zms.controller;

import com.zdw.zms.entity.WiselyMessage;
import com.zdw.zms.entity.WiselyResponseMessage;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class WebSockertController {

    @RequestMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WiselyResponseMessage sayHello(WiselyMessage message) throws InterruptedException {
        Thread.sleep(3000);
        return new WiselyResponseMessage("欢迎使用websocket: "+message.getName());
    }

   @RequestMapping("/index")
    public String index(){
        return "index";
   }

}
