package com.neet.neetdesign.validator;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/req")
@Validated
public class TestController {

    @PostMapping("/test")
    public RestResult<Object> test(@Valid @RequestBody TestParamVo testParamVo) {
        log.info("testParamVo->{}", testParamVo);
        return RestResult.ok();
    }


}

@Data
class TestParamVo {

    /**
     * 测试 @NotBlankFields 注解
     */
    @NotBlankFields
    List<String> testList;

}