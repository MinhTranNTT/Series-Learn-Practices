package com.pet.sysuser.controller;

import com.pet.common.domain.entity.UmsMenu;
import com.pet.common.domain.service.IUmsMenuService;
import com.pet.common.domain.vo.RouterVO;
import com.pet.common.response.PetResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sys/menu")
@AllArgsConstructor
public class MenuController {
    private final IUmsMenuService menuService;

    @GetMapping("self")
    public PetResult searchSelfMenu(){
        // 获取当前登录的用户id，都在SecurityContextHolder中存储
        List<RouterVO> menusList =  menuService.searchSelMenu();
        return PetResult.success().put("data", menusList);
    }

    /*@GetMapping("list")
    public PetResult searchMenuList(){
        List<RouterVO> menusList =  menuService.searchMenuList();
        return PetResult.success(menusList);
    }
    @GetMapping("{id}")
    public PetResult searchMenuById(@PathVariable("id") Long id ){
        RouterVO menusList = menuService.searchMenuById(id);
        return PetResult.success(menusList);
    }*/

}
