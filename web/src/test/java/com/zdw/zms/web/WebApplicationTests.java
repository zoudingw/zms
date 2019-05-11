package com.zdw.zms.web;

import com.zdw.zms.test.daoTwo.AttRulerDao;
import com.zdw.zms.test.entity.AttRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
        System.out.println(attRule);
    }

}
