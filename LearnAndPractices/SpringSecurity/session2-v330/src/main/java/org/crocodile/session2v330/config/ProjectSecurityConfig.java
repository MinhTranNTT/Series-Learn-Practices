package org.crocodile.session1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http
                // .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // only HTTP
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                .requestMatchers("/contact","/notices","/register").permitAll()
        );
        http.formLogin(withDefaults());
        // http.formLogin(FormLoginConfigurer::disable);
        http.httpBasic(withDefaults());
        // http.httpBasic(HttpBasicConfigurer::disable);
        // http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.withUsername("user").password("{noop}CauBeButChi@2024!!!").authorities("read").build();
    //     // UserDetails user = User.withUsername("user").password("{bcrypt}$2a$15$mvSCtxTZAouxqbpASTshN.kKdGGoML55vDRtdVAfXysTUHgdTVh0W").authorities("read").build();
    //     // UserDetails admin = User.withUsername("admin").password("{noop}54321").authorities("admin").build();
    //     UserDetails admin = User.withUsername("admin")
    //             .password("{bcrypt}$2a$12$.NDUkFUo2hT4BLq9LXmQluk7YXShmWpIGPybrmVy8KWaf6d9HgeM6") // CauBeButChi@2024!!!
    //             .authorities("admin").build();
    //
    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    // @Bean
    // public UserDetailsService userDetailsService(DataSource dataSource) {
    //     return new JdbcUserDetailsManager(dataSource);
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
