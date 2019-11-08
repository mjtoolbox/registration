package com.bcfl.registration;


import com.bcfl.registration.authentication.JwtAuthenticationEntryPoint;
import com.bcfl.registration.authentication.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    // In-memory Authentication
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private DataSource dataSource;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * AuthManagerBuilder provides various authentication mechanism: DB, LDAP, in-memory.
     * Configure AuthenticationManager so that it knows from where to load user for matching credentials.
     * UserDetailService can use loadUserByUsername() which can be customized.
     * jdbcAuthentication uses DB to authenticate, but it is expecting exact table/column names in DB tables (role, users).
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
    }


    /**
     * For DEV purpose, permit all traffic and disable security.
     * http.authorizeRequests().antMatchers("/").permitAll();
     * <p>
     * For Basic Authentication Security,
     * http
     * .authorizeRequests()
     * .anyRequest().authenticated()
     * .and()
     * .formLogin().and()
     * .httpBasic().and()
     * .cors();
     * <p>
     * Make sure we use stateless session; session won't be used to store user's state.
     * Add a filter to validate the tokens with every request.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * JWT Authentication
         */
        http
                .csrf().disable()
                // Don't authenticate this particular request
                .authorizeRequests().antMatchers("/token/**").permitAll()
                // All other request need to be authenticated
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }


}
