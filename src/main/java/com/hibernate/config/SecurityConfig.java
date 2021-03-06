package com.hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/perfomances/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/perfomances-sessions/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/stages/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/perfomances/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/perfomance-sessions/").permitAll()
                .antMatchers(HttpMethod.GET, "/perfomance-sessions/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/perfomance-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/perfomance-sessions/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/orders/complete").permitAll()
                .antMatchers(HttpMethod.GET, "/orders").permitAll()
                .antMatchers(HttpMethod.GET, "/shopping-carts/by-user").permitAll()
                .antMatchers(HttpMethod.GET, "/shopping-carts/perfomance-sessions").permitAll()
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

}
