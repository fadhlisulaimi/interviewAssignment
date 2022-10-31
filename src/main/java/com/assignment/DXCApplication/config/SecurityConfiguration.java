//package com.assignment.DXCApplication.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@EnableMethodSecurity
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();
//        userDetailService.createUser(User.builder().username("user").password("password").roles("USER").build());
//        userDetailService.createUser(User.builder().username("admin").password("password").roles("ADMIN").build());
//        return userDetailService;
//    }
//
//}
