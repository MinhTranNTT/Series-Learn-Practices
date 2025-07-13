package org.blog.aspect;

import org.blog.entity.Customer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("authService")
public class AuthService {
    public boolean isOwner(Long requestedId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof Customer)) {
            return false;
        }
        Customer user = (Customer) auth.getPrincipal();
        return user.getId() == requestedId;
    }
}
