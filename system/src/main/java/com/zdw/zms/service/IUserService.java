package com.zdw.zms.service;


import com.zdw.zms.entity.User;

public interface IUserService {

    public int updateUserNameById(String name, Long id);

    public User getUserByUserName(String name);
}
