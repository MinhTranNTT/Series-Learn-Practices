package com.pet.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pet.auth.domain.entity.UmsMenu;
import com.pet.auth.domain.entity.UmsRole;
import com.pet.auth.domain.mapper.UmsMenuMapper;
import com.pet.auth.domain.mapper.UmsRoleMapper;
import com.pet.auth.domain.service.IUmsMenuService;
import com.pet.auth.domain.service.IUmsRoleService;
import org.springframework.stereotype.Service;

@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {

}
