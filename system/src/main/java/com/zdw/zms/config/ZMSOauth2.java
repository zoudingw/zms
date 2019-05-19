package com.zdw.zms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ZMSOauth2 {

    @Configuration
    @EnableResourceServer
    protected static class ZmsOAuthResourceConfiguration extends ResourceServerConfigurerAdapter {


        @Autowired
        RoleHierarchyVoter roleHierarchyVoter;

        /*
         * @Author zoudingwei
         * @Description 设置角色层级关系，如admin用户级别大于user用户，所以user用户能访问的admin用户也能访问
         * @Date 2019/5/12 6:31 PM
         * @Param []
         * @return org.springframework.security.access.expression.SecurityExpressionHandler<org.springframework.security.web.FilterInvocation>
         **/
        @Bean
        CustomAccessDecisionManager accessDecisionManager(){
            List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
            OAuth2WebSecurityExpressionHandler handler = new OAuth2WebSecurityExpressionHandler();
            handler.setRoleHierarchy(roleHierarchy);
            WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
            webExpressionVoter.setExpressionHandler(handler);
            decisionVoters.add(webExpressionVoter);
            decisionVoters.add(roleHierarchyVoter);
            return new CustomAccessDecisionManager(decisionVoters);
        }

        @Autowired
        RoleHierarchy roleHierarchy;

        @Autowired
        CustomFilterInvocationSecurityMetaDataSource customFilterInvocationSecurityMetaDataSource;



        @Override
        public void configure(HttpSecurity http) throws Exception {

            http.
                    authorizeRequests()
                    .antMatchers("/**/**.html","/**/chat/**")
                    .permitAll()
                    .antMatchers("/**/**.js")
                    .permitAll()
                    .accessDecisionManager(accessDecisionManager())
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                            o.setSecurityMetadataSource(customFilterInvocationSecurityMetaDataSource);
                          //  o.setAccessDecisionManager(accessDecisionManager());
                            return o;
                        }
                    })
                    .antMatchers("/user/**")
                    .permitAll()
                    .antMatchers("/system/**").authenticated();

        }


    }

    @Configuration
    @EnableAuthorizationServer
    protected static class ZmsOAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        DataSource dataSource;

        @Bean
        TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        AuthenticationManager authenticationManagerBean;


        @Bean
        ClientDetailsService detailsService() {
            return new JdbcClientDetailsService(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

            endpoints.tokenStore(tokenStore())
                    .authenticationManager(authenticationManagerBean)
                    .exceptionTranslator(loggingExceptionTranslator());
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(detailsService());
        }


        @Bean
        public WebResponseExceptionTranslator loggingExceptionTranslator() {
            return new DefaultWebResponseExceptionTranslator() {
                @Override
                public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                    // This is the line that prints the stack trace to the log. You can customise this to format the trace etc if you like
                    e.printStackTrace();

                    // Carry on handling the exception
                    ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                    OAuth2Exception excBody = responseEntity.getBody();
                    return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
                }
            };
        }

    }


}
