package com.zdw.zms.test.config;

import org.springframework.amqp.core.*;
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


    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description :fanout 模式，广播模式
      */
    @Bean
    FanoutExchange fanoutExchange(){
        //true 表示是否持久化到磁盘盘，false表示长时间未使用是否删除
        return new FanoutExchange("fanoutexchange",true,false);
    }

    @Bean
    public Queue queue2(){
        return new Queue("test2");
    }

    @Bean
    Binding bindingOne(){
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTwo(){
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description :topic 模式
      */

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Queue queue3(){
        return new Queue("phone");
    }

    @Bean
    Binding binding3(){
        return BindingBuilder.bind(queue3()).to(topicExchange()).with("#.phone.#");
    }

    @Bean
    public Queue queue4(){
        return new Queue("huawei");
    }

    @Bean
    Binding binding4(){
        return BindingBuilder.bind(queue4()).to(topicExchange()).with("huawei.#");
    }

    @Bean
    public Queue queue5(){
        return new Queue("apple");
    }

    @Bean
    Binding binding5(){
        return BindingBuilder.bind(queue5()).to(topicExchange()).with("apple.#");
    }

}
