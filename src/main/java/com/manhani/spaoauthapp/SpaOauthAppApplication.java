package com.manhani.spaoauthapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

@EnableResourceServer
@SpringBootApplication
public class SpaOauthAppApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpaOauthAppApplication.class, args);
    }

    @Bean
    protected ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
        return new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(final HttpSecurity http) throws Exception {
                http.authorizeRequests().antMatchers("/", "/index.html", "/sign-in-widget-config").permitAll()
                        .anyRequest().authenticated();
            }
        };
    }
}

@EnableGlobalMethodSecurity(prePostEnabled = true)
class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
