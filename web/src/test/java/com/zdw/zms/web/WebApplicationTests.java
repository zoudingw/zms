package com.zdw.zms.web;

import com.alibaba.fastjson.JSONObject;
import com.zdw.zms.dao.RightDao;
import com.zdw.zms.entity.MyRight;
import com.zdw.zms.test.MQtest.MQTest;
import com.zdw.zms.test.daoTwo.AttRulerDao;
import com.zdw.zms.test.entity.AttRule;
import com.zdw.zms.test.mailTest.MailSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void test11() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/mappers/system/mappers.test/fileTest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
        System.out.println(mvc);
    }

    @Autowired
    AttRulerDao attRulerDao;

    @Test
    public void test22(){
        AttRule attRule = attRulerDao.selectByPrimaryKey("1");
        System.out.println(JSONObject.toJSONString(attRule));
    }

    @Autowired
    RightDao rightDao;

    @Test
    public  void test33(){
        List<MyRight> aLl = rightDao.getALl();
        System.out.println(JSONObject.toJSONString(aLl));
    }

    @Autowired
    MQTest mqTest;

    @Test
    public  void mqtest(){
       // mqTest.directSendMesage();

        mqTest.fanoutSendMessage();
        mqTest.topicSendMessage();
    }

    @Autowired
    MailSend mailSend;

    @Test
    public void test77() throws Exception{
        mailSend.sendThymeleafMail();
    }

}
