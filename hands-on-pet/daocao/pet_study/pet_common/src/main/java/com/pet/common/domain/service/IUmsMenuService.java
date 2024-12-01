package com.pet.common.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pet.common.domain.entity.UmsMenu;
import com.pet.common.domain.vo.RouterVO;

import java.util.List;

public interface IUmsMenuService extends IService<UmsMenu> {

    List<RouterVO> searchSelMenu();
}
