package com.zdw.zms.service;

import com.zdw.zms.entity.Role;
import com.zdw.zms.entity.User;
import com.zdw.zms.entity.UserRole;
import com.zdw.zms.entity.ZmsUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZmsUserDetailService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Autowired
    IUserRole userRole;

    @Autowired
    IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userByUserName = userService.getUserByUserName(s);
        List<Role> roles = new ArrayList<>();
        List<UserRole> userRoles = userRole.selectByPrimaryByUserId(userByUserName.getUserId());
        for (UserRole userRole : userRoles) {
            Role role = roleService.selectByPrimaryKey(userRole.getRoleId());
            roles.add(role);
        }

        userByUserName.setRoleList(roles);
        ZmsUserDetail zmsUserDetail = new ZmsUserDetail(userByUserName);
        return zmsUserDetail;
    }
}
