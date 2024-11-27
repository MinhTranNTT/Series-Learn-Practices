package com.pet.auth.controller;

import com.pet.common.domain.entity.UmsSysUser;
import com.pet.common.domain.service.IUmsSysUserService;
import com.pet.common.response.PetResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ums/sysuser")
@AllArgsConstructor
public class UmsSysUserController {
    private final IUmsSysUserService sysUserService;

    /*
     * 新增用户接口
     * */
    @PostMapping
    public PetResult addSysUser(@RequestBody UmsSysUser user) {
        boolean save = sysUserService.save(user);
        if(save){
            return PetResult.success();
        }
        return PetResult.error();
    }
    /*
     * 查询用户接口
     * */
    @GetMapping
    public PetResult selectSysUser(){
        List<UmsSysUser> list = sysUserService.list();
        list.forEach(System.out::println);
        return PetResult.success(list);
    }
}
