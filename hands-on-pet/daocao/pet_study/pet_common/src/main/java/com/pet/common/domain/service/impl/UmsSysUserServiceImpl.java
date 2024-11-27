package com.pet.common.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pet.common.domain.entity.UmsSysUser;
import com.pet.common.domain.mapper.UmsSysUserMapper;
import com.pet.common.domain.service.IUmsSysUserService;
import org.springframework.stereotype.Service;

@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {

}
