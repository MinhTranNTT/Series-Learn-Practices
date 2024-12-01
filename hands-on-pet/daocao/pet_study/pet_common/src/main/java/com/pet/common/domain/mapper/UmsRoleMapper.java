package com.pet.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pet.common.domain.entity.UmsRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UmsRoleMapper extends BaseMapper<UmsRole> {
    List<Long> selectByUserId(Long userId);
}
