package com.pet.common.domain.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pet.common.domain.entity.UmsMenu;
import com.pet.common.domain.mapper.UmsMenuMapper;
import com.pet.common.domain.mapper.UmsRoleMapper;
import com.pet.common.domain.service.IUmsMenuService;
import com.pet.common.domain.vo.RouterVO;
import com.pet.common.utils.security.PetSecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {
    private final UmsRoleMapper roleMapper;

    @Override
    public List<RouterVO> searchSelMenu() {
        Long userId = PetSecurityUtil.getUserId();
        List<Long> roleIds = roleMapper.selectByUserId(userId);
        log.info("roleIds============>"+roleIds);
        // 根据角色id查用户的权限列表
        List<UmsMenu> menus = baseMapper.selectByRoleIds(roleIds);
        log.info("menus=========>"+menus);
        List<RouterVO> router =  getRouter(menus);
        router.forEach(System.out::println);
        return router;
    }

    // 功能是将路由列表变成树状的形式
    private List<RouterVO> getRouter(List<UmsMenu> menusList){
        // menusList是全部的路由列表
        List<RouterVO> routerVos = new ArrayList<>();
        // 获取所有的一级路由
        List<UmsMenu> parentMenu = menusList.stream().filter(item -> item.getParentId() == 0).toList();
        for(UmsMenu menu:parentMenu){
            RouterVO routerVO = new RouterVO();
            // 数据类型转换
            BeanUtil.copyProperties(menu,routerVO);
            routerVos.add(routerVO);
        }
        // 获取下一级的所有子菜单
        for(RouterVO routerVO:routerVos){
            List<RouterVO> childrenlist = buildTree(menusList,routerVO.getId());
            routerVO.setChildren(childrenlist);
        }
        return routerVos;
    }

    // 递归获取子路径
    private List<RouterVO> buildTree(List<UmsMenu> parentMenu,Long parentId){
        List<RouterVO> childrenList =  new ArrayList<>();
        // 遍历所有数据
        for(UmsMenu menu:parentMenu){
            // 判断menu的parentId是否与传进来的parentId相同
            if(menu.getParentId().equals(parentId)){
                RouterVO routerVO = new RouterVO();
                BeanUtil.copyProperties(menu,routerVO);
                childrenList.add(routerVO);
            }
        }
        // 递归遍历childrenList可能还有的子节点
        for(RouterVO childrenItem:childrenList){
            childrenItem.setChildren(buildTree(parentMenu,childrenItem.getId()));
        }
        return childrenList;
    }

}
