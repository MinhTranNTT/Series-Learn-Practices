package com.pet.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pet.common.domain.entity.UmsSysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UmsSysUserMapper extends BaseMapper<UmsSysUser> {
    UmsSysUser selectUserByUserName(@Param("account") String username, @Param("accountType") int accountType);
}
