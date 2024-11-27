package com.pet.common.domain.service;

import com.pet.common.domain.dto.LoginDto;

public interface IAuthService {
    String login(LoginDto loginDto);
}
