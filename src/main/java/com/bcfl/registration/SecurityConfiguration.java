package com.bcfl.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

//                http.authorizeRequests().antMatchers("/").permitAll();

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic().and()
                .cors();

//                .oauth2Login().and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**/*.{js,html,css}").permitAll()
//                .antMatchers("/", "/students", "/guardians", "/api/user").permitAll()
//                .anyRequest().authenticated();
    }

//    @Bean
//    public RequestCache refererRequestCache() {
//        return new HttpSessionRequestCache() {
//            @Override
//            public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
//                String referrer = request.getHeader("referer");
//                if (referrer != null) {
//                    request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer));
//                }
//            }
//        };
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{noop}pass") // Spring Security 5 requires specifying the password storage format
//                .roles("USER");
//    }

}