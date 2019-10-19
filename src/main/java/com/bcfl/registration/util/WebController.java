package com.bcfl.registration.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin()
@RestController
@Slf4j
public class WebController {

//    private ClientRegistration registration;
//
//    public WebController(ClientRegistrationRepository registrationRepository) {
//        this.registration = registrationRepository.findByRegistrationId("okta");
//    }
//
//    @GetMapping("/api/user")
//    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user) {
//        if (user == null) {
//            log.info("************ user is null");
//            return new ResponseEntity<>("", HttpStatus.OK);
//        } else {
//            log.info("************ user : " + user);
//            return ResponseEntity.ok().body(user.getAttributes());
//        }
//    }
//
//    @PostMapping("/api/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request,
//                                    @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken) {
//        // send logout URL to client so they can initiate logout
//        String logoutUrl = this.registration.getProviderDetails()
//                .getConfigurationMetadata().get("end_session_endpoint").toString();
//
//        Map<String, String> logoutDetails = new HashMap<>();
//        logoutDetails.put("logoutUrl", logoutUrl);
//        logoutDetails.put("idToken", idToken.getTokenValue());
//        request.getSession(false).invalidate();
//        return ResponseEntity.ok().body(logoutDetails);
//    }
//
    @GetMapping("/basicauth")
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
