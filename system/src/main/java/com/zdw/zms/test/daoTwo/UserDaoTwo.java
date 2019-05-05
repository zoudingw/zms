package com.zdw.zms.test.daoTwo;

import com.zdw.zms.entity.User;
import org.apache.ibatis.annotations.Mapper;

public interface UserDaoTwo {
    User selectByPrimaryKey(Integer userId);

    User getByName(String userName);

    int deleteByPrimaryKey(Integer userId);

    int insertUser(User user);

    int insertSelective(User user);

    int updateByPrimaryKeySelective(User user);

}
