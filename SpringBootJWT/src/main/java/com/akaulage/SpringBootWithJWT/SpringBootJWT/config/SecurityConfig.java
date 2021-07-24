package com.akaulage.SpringBootWithJWT.SpringBootJWT.config;

import com.akaulage.SpringBootWithJWT.SpringBootJWT.filter.JwtFilter;
import com.akaulage.SpringBootWithJWT.SpringBootJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

// security configuration file and extends WebSecurityConfigurerAdapter

//Here we doing spring security configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    JwtFilter jwtFilter;

    // we overiding configure method having AuthenticationManagerBuilder as parameter
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Spring provide custom userDetailsService

        auth.userDetailsService(userService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //we have to bypass authenicate api for token generation (without spring security we have to generate token)
    //and also permit to authentication follows for  other request except authenticate

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authentication")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()   //not used session mamagement.................
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
