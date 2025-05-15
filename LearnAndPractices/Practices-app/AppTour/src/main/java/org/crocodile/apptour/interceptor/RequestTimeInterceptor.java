package org.crocodile.apptour.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestTimeInterceptor.class);
    private static final String START_TIME_ATTRIBUTE = "start_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_ATTRIBUTE, startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute(START_TIME_ATTRIBUTE);
        long endTime = System.currentTimeMillis();
        long handlingTime = endTime - startTime;
        logger.info("Request handling time: {}ms", handlingTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // do nothing
    }
}
