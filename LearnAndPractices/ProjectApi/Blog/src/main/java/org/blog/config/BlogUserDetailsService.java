package org.blog.config;

import lombok.RequiredArgsConstructor;
import org.blog.entity.Customer;
import org.blog.mapper.CustomerMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {

    private final CustomerMapper customerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = Optional.ofNullable(customerMapper.getCustomerByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("User details not found for the user: " + username));
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(customer.getRole()));
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
}
