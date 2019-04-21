package com.zdw.zms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class ZMSOauth2 {

    @Configuration
    @EnableResourceServer
    protected  static class ZmsOAuthResourceConfiguration extends ResourceServerConfigurerAdapter{


        @Override
        public void configure(HttpSecurity http) throws Exception {

            http.
                    authorizeRequests()
                    .antMatchers("/user/**")
                    .permitAll()
            .antMatchers("/system/**").authenticated();
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected  static class ZmsOAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{

        @Autowired
        DataSource dataSource;

        @Bean
        TokenStore tokenStore(){
            return new JdbcTokenStore(dataSource);
        }

        @Autowired
        PasswordEncoder passwordEncoder;

        @Autowired
        AuthenticationManager authenticationManagerBean;


        @Bean
        ClientDetailsService detailsService(){
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
