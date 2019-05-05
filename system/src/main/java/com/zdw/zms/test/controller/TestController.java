package com.zdw.zms.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.zdw.zms.dao.UserDao;
import com.zdw.zms.entity.User;
import com.zdw.zms.test.config.Book;
import com.zdw.zms.test.daoTwo.UserDaoTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author zoudingwei
 * @Date 2019/5/4 9:46 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    Book book;

    @GetMapping(value = "/bookPrint",produces = "text/plain;charset=UTF-8")
    public String printBook(HttpServletRequest request, HttpServletResponse response){
        return JSONObject.toJSONString(book);
    }

    @PostMapping(value = "upload")
    public  String fileUopload(MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) throws Exception{

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String datesufix = format.format(new Date());
        //获取classpath:temp路径
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        List<String> paths = new ArrayList<>();
        int i = 0;
        for (MultipartFile file : files) {
            i++;
            String filename = file.getOriginalFilename();
            String substring = filename.substring(filename.lastIndexOf("."), filename.length());
            String s = path +"static/"+ i + "_" + datesufix + substring;
            File  file1 = new File(s);
            file.transferTo(file1);
            paths.add(s);


        }
        return JSONObject.toJSONString(paths);

    }

    @GetMapping("/fileTest")
    public String filePath(){
        String path = getClass().getClassLoader().getResource("/static/").getPath();
        return path;
    }

    @Autowired
    UserDao daoOne;

    @Autowired
    UserDaoTwo daoTwo;

    /*
     * @Author zoudingwei
     * @Description  多数据源测试
     * @Date 2019/5/5 9:34 PM
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping(value = "/user")
    public String getUseOne(){
        User admin2 = daoTwo.getByName("admin2");
        User admin = daoOne.getByName("admin");
        return "admin: "+JSONObject.toJSONString(admin)+" admin2: "+JSONObject.toJSONString(admin2);
    }
}
