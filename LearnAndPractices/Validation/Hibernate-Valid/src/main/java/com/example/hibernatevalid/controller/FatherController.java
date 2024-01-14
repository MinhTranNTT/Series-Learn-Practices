package com.example.hibernatevalid.controller;

import com.example.hibernatevalid.domain.Father;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Validated
@ResponseBody
public class FatherController {
    @RequestMapping("/father")
    public void father(@RequestBody @Valid Father father, BindingResult result){

        // Handle business logic
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
    }
}
