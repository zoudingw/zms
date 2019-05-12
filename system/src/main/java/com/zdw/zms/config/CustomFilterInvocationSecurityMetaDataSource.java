package com.zdw.zms.config;

import com.zdw.zms.dao.RightDao;
import com.zdw.zms.dao.RoleDao;
import com.zdw.zms.dao.RoleRightDao;
import com.zdw.zms.entity.MyRight;
import com.zdw.zms.entity.Role;
import com.zdw.zms.entity.RoleRight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomFilterInvocationSecurityMetaDataSource
 * @Description 动态权限控制
 * @Author zoudingwei
 * @Date 2019/5/12 3:54 PM
 * @Version 1.0
 **/
@Component
public class CustomFilterInvocationSecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    RightDao rightDao;

    @Autowired
    RoleRightDao roleRightDao;

    @Autowired
    RoleDao roleDao;

    AntPathMatcher antPathMatcher = new AntPathMatcher();


    /*
     * @Author zoudingwei
     * @Description 根据请求的url，获取访问该资源需要的角色信息
     * @Date 2019/5/12 4:17 PM
     * @Param [o]
     * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>
     **/
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //获取所有权限的url
        List<MyRight> aLl = rightDao.getALl();
        for (MyRight myRight : aLl) {
            //校验请求的URL和哪个权限的url匹配
            if(antPathMatcher.match(myRight.getUrl(),requestUrl)){
                //获取该权限的所有角色
                List<RoleRight> roleRights = roleRightDao.selectByPrimaryByRirghtId(myRight.getRightId());
                String[] rolenames = new String[roleRights.size()];
                for (int i=0; i< roleRights.size(); i++) {
                    Role role = roleDao.selectByPrimaryKey(roleRights.get(i).getRoleId());
                    rolenames[i] = "ROLE_"+role.getRoleName(); //区分大小写
                }
                return SecurityConfig.createList(rolenames);
            }
        }

        return SecurityConfig.createList("ROLE_LOGIN"); //权限表没有设置，那么该资源就是登录就可以访问
    }

    /*
     * @Author zoudingwei
     * @Description spring security 启动时校验，如果不想校验返回null
     * @Date 2019/5/12 4:19 PM
     * @Param []
     * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>
     **/
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
