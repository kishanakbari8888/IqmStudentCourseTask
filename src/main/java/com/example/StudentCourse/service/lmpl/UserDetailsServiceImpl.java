package com.example.StudentCourse.service.lmpl;

import java.sql.SQLException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.dao.UserDao;
import com.example.StudentCourse.entities.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDetails userdetails= userDao.findById(username);
            return userdetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
