package com.example.StudentCourse.JwtToken;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private JwtAuthenticationEntryPoint point;
    private JwtAuthenticationFilter filter;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    public SecurityConfig(JwtAuthenticationEntryPoint point, JwtAuthenticationFilter filter, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.point = point;
        this.filter = filter;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/students/**").hasRole("STUDENT")
                .antMatchers("/api/student/**")
//                .hasRole("ADMIN")
                .authenticated()
                .and().exceptionHandling().authenticationEntryPoint(point)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }


}