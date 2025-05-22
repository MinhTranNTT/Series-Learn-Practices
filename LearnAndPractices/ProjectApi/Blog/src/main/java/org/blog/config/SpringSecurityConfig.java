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
        http
                .csrf(csrfConfig -> csrfConfig.disable())
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // .and()
                .authorizeHttpRequests(req -> req
                        // .antMatchers("/contact","/notices","/customer").permitAll()
                        // .anyRequest().authenticated()
                        .antMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
                        .antMatchers("/myBalance").hasAnyAuthority("VIEWBALANCE", "VIEWACCOUNT")
                        .antMatchers("/myLoans").hasAuthority("VIEWLOANS")
                        .antMatchers("/myCards").hasAuthority("VIEWCARDS")
                        .antMatchers("/user").authenticated()
                );

        http.formLogin(flc -> flc.disable());
        http.httpBasic(hsc -> hsc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
