package com.pet.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pet.common.domain.entity.UmsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {
    List<UmsMenu> selectByRoleIds(@Param("roleIds") List<Long> roleIds);
}
