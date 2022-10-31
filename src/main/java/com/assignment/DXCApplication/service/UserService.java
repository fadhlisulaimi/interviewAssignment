//package com.assignment.DXCApplication.service;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class UserService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //usual logic to get user from database and check role
//        //but for interview purpose, i will just return a new hardcoded User from spring security
//        return new User("dxc_admin", "password", new ArrayList<>());
//    }
//}
