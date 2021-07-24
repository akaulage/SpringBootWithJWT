package com.akaulage.SpringBootWithJWT.SpringBootJWT.controller;

import com.akaulage.SpringBootWithJWT.SpringBootJWT.Utility.JWTUtility;
import com.akaulage.SpringBootWithJWT.SpringBootJWT.common.JwtRequest;
import com.akaulage.SpringBootWithJWT.SpringBootJWT.common.JwtResponse;
import com.akaulage.SpringBootWithJWT.SpringBootJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String Home() {
        return "Spring Booot with JWT token Authentication World";
    }

    @PostMapping("/authentication")
    public JwtResponse getJwtToken(@RequestBody JwtRequest jwtrequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtrequest.getUsername(),
                            jwtrequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {

           throw new Exception("Invalid Username and Password",e);
        }
        //UserDetails object from spring security
        final UserDetails userDetails=userService.loadUserByUsername(jwtrequest.getUsername());
        final String token=jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
