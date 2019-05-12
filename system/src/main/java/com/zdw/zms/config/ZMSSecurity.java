package com.zdw.zms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true)
public class ZMSSecurity extends WebSecurityConfigurerAdapter {


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/**");
    }

    @Autowired
    UserDetailsService zmsUserDetailService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(zmsUserDetailService).passwordEncoder(passwordEncoder());

    }



    /*
     * @Author zoudingwei
     * @Description 角色继承配置，如admin用户是既有user权限也是有admin权限的,配置后具有
     * ROLE_ADMIN 的用户既可以访问admin用户的资源，也可以访问用户的的资源
     * @Date 2019/5/12 2:55 PM
     * @Param []
     * @return org.springframework.security.access.hierarchicalroles.RoleHierarchy
     **/
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchy roleHierarchy = new RoleHierarchyImpl();
        String hierarcy  =  "ROLE_admin > ROLE_user"; //区分大小写
        ((RoleHierarchyImpl) roleHierarchy).setHierarchy(hierarcy);
        return roleHierarchy;
    }

    @Bean
    public  RoleHierarchyVoter roleHierarchyVoter(){
        return new RoleHierarchyVoter(roleHierarchy());
    }

}

