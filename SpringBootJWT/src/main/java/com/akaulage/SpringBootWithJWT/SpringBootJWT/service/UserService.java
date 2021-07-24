package com.akaulage.SpringBootWithJWT.SpringBootJWT.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

//implementing UserDetailsService provided by spring
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the User from the database...but we are taking dummy username  & password for testing purpose

        //we are taking User module from spring framework security ,it is taking username,password and role as arraylist

        return new User("admin","admin123",new ArrayList<>());
    }
}
