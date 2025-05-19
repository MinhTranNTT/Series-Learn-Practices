package org.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(req -> req
                .antMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                .antMatchers("/contact","/notices").permitAll())
                .formLogin(flc -> flc.disable())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userRead = User.withUsername("user").password("{noop}123456").authorities("read").build();
        UserDetails userAdmin = User.withUsername("admin").password("{bcrypt}$2a$12$SBQ6B4WuzbNaMYdU0sGvYuy1z/iCoq0Ll29.meIBndugoYezBwoL6").authorities("admin").build();
        return new InMemoryUserDetailsManager(userRead, userAdmin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
