package org.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.blog.entity.Customer;
import org.blog.entity.OwnerCheck;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class OwnerCheckAspect {
    // @Before("@annotation(ownerCheck) && args(.., request, authentication)")
    // public void checkOwner(JoinPoint joinPoint, OwnerCheck ownerCheck, HttpServletRequest request, Authentication authentication) {
    //     String pathIdParam = ownerCheck.pathId();
    //     String idStr = request.getRequestURI().replaceAll(".*/", "");
    //     Long requestedId = Long.valueOf(idStr);
    //
    //     Customer userDetails = (Customer) authentication.getPrincipal();
    //     Long currentUserId = userDetails.getId();
    //
    //     if (!requestedId.equals(currentUserId)) {
    //         throw new AccessDeniedException("Unauthorized access");
    //     }
    // }

    @Before("@annotation(ownerCheck)")
    public void checkOwner(JoinPoint joinPoint, OwnerCheck ownerCheck) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Customer)) {
            throw new AccessDeniedException("Unauthorized access");
        }

        String uri = request.getRequestURI(); // /myAccount/5
        String idStr = uri.substring(uri.lastIndexOf("/") + 1);
        Long requestedId = Long.valueOf(idStr);

        Customer customer = (Customer) authentication.getPrincipal();
        Long currentUserId = customer.getId();

        if (!requestedId.equals(currentUserId)) {
            throw new AccessDeniedException("Unauthorized access");
        }
    }
}
