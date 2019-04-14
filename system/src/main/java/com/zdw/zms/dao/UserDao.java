package com.zdw.zms.dao;

import com.zdw.zms.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao{
    User selectByPrimaryKey(Integer userId);

    User getByName(String userName);

    int deleteByPrimaryKey(Integer userId);

    int insertUser(User user);

    int insertSelective(User user);

    int updateByPrimaryKeySelective(User user);

}
