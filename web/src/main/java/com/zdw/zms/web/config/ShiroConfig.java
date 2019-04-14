package com.zdw.zms.web.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

   /* @Bean
    ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChain = new LinkedHashMap<>();
        filterChain.put("/static/**", "anon");
        filterChain.put("/logout", "logout");
        filterChain.put("/**", "anon");
        // filterChain.put("/**", "authc");
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/upload");
        factoryBean.setUnauthorizedUrl("/403");
        factoryBean.setFilterChainDefinitionMap(filterChain);
        return factoryBean;
    }

    @Bean
    Realm realm(){
        return new MyShiroRealm();
    }

    @Bean
    SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    *//*
     * @author zoudw
     * @param [securityManager]
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @date 2019/3/20
     * @description :开启授权注解扫瞄
     *//*
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }*/

}
