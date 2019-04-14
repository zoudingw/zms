package com.zdw.zms.dao;

import com.zdw.zms.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/15
 * Description:
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
@Mapper
public interface RoleDao {
    Role selectByPrimaryKey(Integer roleId);
}
