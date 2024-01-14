package com.example.hibernatevalid.controller;

import com.example.hibernatevalid.domain.User;
import com.example.hibernatevalid.result.ResultVo;
import com.example.hibernatevalid.validation.ValidList;
import jakarta.validation.groups.Default;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class ListController {
    @RequestMapping("/addByListT")
    public ResultVo addList(@RequestBody @ValidList(groupings = {User.Add.class, Default.class}, quickFail = true) List<User> user) {
        return ResultVo.success();
    }

    // quickFail = true
    /*{
        "success": false,
            "code": "1000",
            "msg": "The parameter is incorrect",
            "data": {
        "0": {
            "name": "名字不能为空",
                    "birthday": "出生日期不能为空",
                    "id": "插入数据时主键不可以有值",
                    "email": "邮件不能为空",
                    "age": "年龄不能为空"
        }
    }
    }*/

    @RequestMapping("/addByListF")
    public ResultVo addListF(@RequestBody @ValidList(groupings = {User.Add.class, Default.class}, quickFail = false) List<User> user) {
        return ResultVo.success();
    }
}
