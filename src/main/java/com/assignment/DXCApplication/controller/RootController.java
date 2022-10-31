package com.assignment.DXCApplication.controller;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping(value = "/")
    public String getRootPage() {
        return appName;
    }
}