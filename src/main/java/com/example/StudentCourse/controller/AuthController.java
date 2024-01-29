package com.example.StudentCourse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentCourse.JwtToken.JwtHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserDetailsService userDetailsService;
    private AuthenticationManager manager;
    private JwtHelper jwtHelper;

    public AuthController(UserDetailsService userDetailsService, AuthenticationManager manager, JwtHelper jwtHelper) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> request) {

        String email = (String) request.get("email");
        String password = (String) request.get("password");

        this.doAuthenticate(email,password);


        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = this.jwtHelper.generateToken(userDetails);

        Map<String,Object> res = new HashMap<>();
        res.put("token",token);
        res.put("username",userDetails.getUsername());
        return res;
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
