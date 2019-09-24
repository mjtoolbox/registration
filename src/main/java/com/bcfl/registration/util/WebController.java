package com.bcfl.registration.util;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

//    @GetMapping("/")
//    public String home(@AuthenticationPrincipal OidcUser oidcUser) {
//        return "Welcome, " + oidcUser.getFullName();
//    }

    @GetMapping("/")
    public String home() {
        return "Welcome to my world!";
    }


//    @GetMapping("/attributes")
//    public String attributes(@AuthenticationPrincipal OidcUser oidcUser) {
//        return oidcUser.getAttributes().toString();
//    }
//
//    @GetMapping("/authorities")
//    public String authorities(@AuthenticationPrincipal OidcUser oidcUser) {
//        return oidcUser.getAuthorities().toString();
//    }
}
