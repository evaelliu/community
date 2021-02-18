package com.evael.community.config;

import com.evael.community.auth.domain.authentication.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.SessionAttributes;


@Configuration
@EnableWebSecurity
@SessionAttributes("authorizationRequest")
@Order(SecurityProperties.BASIC_AUTH_ORDER-10)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailInMongo")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(generalAuthenticationProvider());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //配置全局设置
    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //设置UserDetailsService以及密码规则
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    protected GeneralAuthenticationProvider generalAuthenticationProvider() {
        GeneralAuthenticationProvider generalAuthenticationProvider = new GeneralAuthenticationProvider();
        generalAuthenticationProvider.setUserDetailsService(userDetailsService);
        generalAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return generalAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable()
                .logout()
                .and()
                .formLogin()
                .and()
                .httpBasic().disable();
    }

}
