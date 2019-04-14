package com.zdw.zms.service;

import com.zdw.zms.dao.UserDao;
import com.zdw.zms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/15
 * Description:
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
@Service("userDetailsService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;


    @Override
    public int updateUserNameById(String name, Long id) {
        return 0;
    }

    public User getUserByUserName(String name){
        User user = userDao.getByName(name);
        return user;
    }

}

