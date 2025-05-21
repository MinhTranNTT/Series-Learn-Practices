package org.blog.config;

import org.blog.exceptionhandling.CustomAccessDeniedHandler;
import org.blog.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                // .requiresChannel(crr -> crr.anyRequest().requiresSecure())
                .authorizeHttpRequests(req -> req
                .antMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                .antMatchers("/contact","/notices","/customer").permitAll())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin(flc -> flc.disable())

                .httpBasic(hsc -> hsc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()))
                // .exceptionHandling(ehc -> ehc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())); // an Global config
                .exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler())); // an Global config

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
