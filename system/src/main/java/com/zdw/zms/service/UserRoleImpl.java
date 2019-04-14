package com.zdw.zms.service;

import com.zdw.zms.dao.UserRoleDao;
import com.zdw.zms.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleImpl implements IUserRole {

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public List<UserRole> selectByPrimaryByUserId(Integer userId) {
        return userRoleDao.selectByPrimaryByUserId(userId);
    }

    @Override
    public List<UserRole> selectByPrimaryByRoleId(Integer roleId) {
        return userRoleDao.selectByPrimaryByRoleId(roleId);
    }
}
