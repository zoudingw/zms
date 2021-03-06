package com.zdw.zms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/20
 * Description:
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    @RequestMapping("/login")
   // @PreAuthorize("hasAnyRole('user')")
    public  String login(HttpServletRequest request, Map<String,Object> map){
        String attribute = (String) request.getAttribute("shiroLoginFailure");
        if (null != attribute) {
            System.out.println(attribute);
        }
        map.put("msg",attribute);
        return "login";
    }
    /*@RequestMapping({"/","/index"})
    public String index(){
        return"/upload";
    }
*/
    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
