package com.pet.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pet.auth.domain.entity.UmsSysUser;
import com.pet.auth.domain.mapper.UmsSysUserMapper;
import com.pet.auth.domain.service.IUmsSysUserService;
import org.springframework.stereotype.Service;

@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {

}
