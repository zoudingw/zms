package com.zdw.zms.service;


import com.zdw.zms.entity.UserRole;

import java.util.List;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/15
 * Description:
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
public interface IUserRole {

    List<UserRole> selectByPrimaryByUserId(Integer userId);
    List<UserRole> selectByPrimaryByRoleId(Integer roleId);


}
