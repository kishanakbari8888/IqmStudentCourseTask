//package com.example.StudentCourse.service.lmpl;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.StudentCourse.entities.UserDetailsImpl;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<GrantedAuthority> roles = new LinkedList<>();
//        roles.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
//        return new UserDetailsImpl("kishan","12345678",roles);
//    }
//}
