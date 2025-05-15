package org.crocodile.apptour.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.crocodile.apptour.entity.UserTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class UserTypeConfig {
    private UserType userType;

    @Data
    public static class UserType {
        private UserTypeEnum admin;
        private UserTypeEnum guest;
        private UserTypeEnum premium;
    }

}
