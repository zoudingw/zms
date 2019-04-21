package com.zdw.zms.service;

import com.zdw.zms.dao.RoleDao;
import com.zdw.zms.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {


    @Autowired
    RoleDao roleDao;


    @Override
    public Role selectByPrimaryKey(Integer roleId) {
        return roleDao.selectByPrimaryKey(roleId);
    }
}
