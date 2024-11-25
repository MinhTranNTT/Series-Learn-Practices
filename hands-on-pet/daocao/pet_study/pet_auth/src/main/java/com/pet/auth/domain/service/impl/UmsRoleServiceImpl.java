package com.pet.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pet.auth.domain.entity.UmsRole;
import com.pet.auth.domain.mapper.UmsRoleMapper;
import com.pet.auth.domain.service.IUmsRoleService;
import org.springframework.stereotype.Service;

@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

}
