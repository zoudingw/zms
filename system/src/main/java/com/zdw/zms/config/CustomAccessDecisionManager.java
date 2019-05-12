package com.zdw.zms.config;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomAccessDecisionManager
 * @Description 角色信息比对，就是访问该资源所需要的角色与访问该url的用户的角色信息的比较
 * @Author zoudingwei
 * @Date 2019/5/12 4:20 PM
 * @Version 1.0
 **/
public class CustomAccessDecisionManager extends AffirmativeBased {

    private  List<AccessDecisionVoter<?>> decisionVoters;


    public CustomAccessDecisionManager(List<AccessDecisionVoter<? extends Object>> decisionVoters) {
        super(decisionVoters);
        this.decisionVoters = decisionVoters;
    }

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        //权限层次校验
        super.decide(authentication,o,collection);

        //获取登录用户的角色信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //获取访问该URL需要的角色信息
        for (ConfigAttribute attribute : collection) {
            //该资源登录即可访问，而且用户已经登录，放行即可
            if("ROLE_LOGIN".equals(attribute.getAttribute()) && authentication instanceof UsernamePasswordAuthenticationToken){
                return;
            }

            //开始校验
           /* for (GrantedAuthority authority : authorities) {
                if(attribute.getAttribute().equals(authority.getAuthority()))
                    //该用户含有访问该资源的角色 直接放行
                    return ;

            }*/
           return;
        }
        throw  new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


}
