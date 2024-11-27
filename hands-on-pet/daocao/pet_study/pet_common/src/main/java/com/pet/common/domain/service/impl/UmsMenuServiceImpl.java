package com.pet.common.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pet.common.domain.entity.UmsMenu;
import com.pet.common.domain.mapper.UmsMenuMapper;
import com.pet.common.domain.service.IUmsMenuService;
import org.springframework.stereotype.Service;

@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {

}
