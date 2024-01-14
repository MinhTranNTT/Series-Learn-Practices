package com.example.hibernatevalid.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /*@NotNull(message = "Primary key cannot have a value")
    private Integer id;

    @NotBlank(message = "The name cannot be blank")
    private String name;

    @NotNull(message = "Date of birth cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "Age cannot be null")
    private Integer age;

    @NotBlank(message = "The message cannot be blank")
    @Email(message = "The email format is incorrect")
    private String email;

    //Verify custom entity class: one-to-one
    @Valid
    private Father father;

    //Verify custom entity class list: one-to-many
    private List<@Valid Son> sons;*/

    //分组校验:Update验证组和Add验证组
    public interface Update{}
    public interface Add{}

    /**
     * 如果指定了验证组，那么该参数就只属于指定的验证组
     *
     * 如果没有指定校验组，那么该参数就只属于默认组
     */


    @Null(message = "插入数据时主键不可以有值",groups = {Add.class})
    @NotNull(message ="更新时需要传主键id过来",groups = {Update.class})
    private Integer id;

    @NotBlank(message = "名字不能为空")
    private String name;

    @NotNull(message = "出生日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotBlank(message = "邮件不能为空")
    @Email(message = "邮件格式不正确")
    private String email;

    //验证自定义实体类：一对一
    @Valid
    private Father father;

    //验证自定义实体类列表：一对多
    private List<@Valid Son> sons;
}
