package com.zdw.zms.controller;

import com.zdw.zms.entity.User;
import com.zdw.zms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/20
 * Description:
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
    @RestController
    @RequestMapping("/user")
    public class UserController {

        @Autowired
    IUserService userService;

        /**ø
         * 用户查询.
         * @return
         */
        @RequestMapping("/view")
       // @PreAuthorize("hasRole('admin')")
       // @RequiresPermissions("user:view")//权限管理;
        public User userInfo(){
            User admin = userService.getUserByUserName("admin");
            return admin  ;
        }

        /**
         * 用户添加;
         * @return
         */
        @RequestMapping("/add")
       // @RolesAllowed("ADMIN")
       // @PreAuthorize("hasRole('USER')")
        //  @RequiresPermissions("user:add")//权限管理;
        public String userInfoAdd(){
            return "userAdd";
        }

        /**
         * 用户删除;
         * @return
         */
        @RequestMapping("/delete")
     //   @RequiresPermissions("user:delete")//权限管理;
        public String userDel(){
            return "userDelete";
        }
}
