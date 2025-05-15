package org.demo.demofeature.config;

import jakarta.servlet.Filter;
import org.demo.demofeature.service.demoScope.PrototypeBean;
import org.demo.demofeature.service.demoScope.SingletonBean;
import org.demo.demofeature.service.demoScope.Student;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
public class ScopeConfig {

    // @Bean
    // @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Student getStudent() {
        return new Student();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SingletonBean singletonBean() {
        return new SingletonBean();
    }

    @Bean
    public FilterRegistrationBean<TimingFilter> timingFilterRegistration() {
        FilterRegistrationBean<TimingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new TimingFilter());
        registration.addUrlPatterns("/*");
        return registration;

    }
}
