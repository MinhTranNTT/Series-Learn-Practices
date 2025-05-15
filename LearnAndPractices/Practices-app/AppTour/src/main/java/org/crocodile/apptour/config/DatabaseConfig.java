package org.crocodile.apptour.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database")
// @PropertySource("classpath:config/${spring.profiles.active}/database.properties")
public class DatabaseConfig {

}
