package org.crocodile.apptour.service.impl;

import lombok.AllArgsConstructor;
import org.crocodile.apptour.config.UserTypeConfig;
import org.crocodile.apptour.dto.Result;
import org.crocodile.apptour.service.HelloService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class HelloServiceImpl implements HelloService {
    private final UserTypeConfig userTypeConfig;

    @Override
    public Result<?> getHello() {
        Map<String, Object> data = new HashMap<>();
        data.put("admin", userTypeConfig.getUserType().getAdmin());
        data.put("guest", userTypeConfig.getUserType().getGuest());
        data.put("premium", userTypeConfig.getUserType().getPremium());

        return Result.ok(data);
    }
}
