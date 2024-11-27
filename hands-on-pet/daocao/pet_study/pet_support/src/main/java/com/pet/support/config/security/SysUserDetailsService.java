package com.pet.support.config.security;

import cn.hutool.core.util.ObjectUtil;
import com.pet.common.domain.entity.UmsMenu;
import com.pet.common.domain.entity.UmsRole;
import com.pet.common.domain.entity.UmsSysUser;
import com.pet.common.domain.mapper.UmsMenuMapper;
import com.pet.common.domain.mapper.UmsSysUserMapper;
import com.pet.common.domain.vo.LoginUserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SysUserDetailsService implements UserDetailsService {
    private final UmsSysUserMapper sysUserMapper;
    private final UmsMenuMapper menuMapper;

    /*
     * 实现方法,在此方法中根据用户名查询用户
     * 账户：电话，邮箱，用户名，通过正则表达式判断账户类型
     * */
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        int accountType = 0;
        // 根据账户查询用户，同时将角色查出来，联查最好不要超过三张表
        UmsSysUser sysUser = sysUserMapper.selectUserByUserName(account,accountType);
        log.info("sysUser========>{}",sysUser);
        // 查询权限是根据角色查询的，首先获取所有角色的id
        if(ObjectUtil.isNotNull(sysUser)){
            List<UmsRole> roleList = sysUser.getRoleList();
            // UmsRole::getRoleId为方法引用
            List<Long> roleIds = roleList.stream().map(UmsRole::getRoleId).collect(Collectors.toList());
            log.info("角色id===>{}",roleIds);
            // 查询所有菜单
            List<UmsMenu> menuList = menuMapper.selectByRoleIds(roleIds);
            // 获取list里面的字段
            List<String> perms = menuList.stream().map(UmsMenu::getPerms).collect(Collectors.toList());
            log.info("角色权限====>{}",perms);
            sysUser.setPerms(perms);
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setSysUser(sysUser);
        loginUserVO.setId(sysUser.getId());
        return loginUserVO;
    }

}
