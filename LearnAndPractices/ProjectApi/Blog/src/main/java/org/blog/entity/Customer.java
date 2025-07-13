package org.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@TableName("customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements UserDetails {

    @TableId(type = IdType.AUTO)
    private long id;
    private String email;
    private String pwd;
    @TableField(value = "role")
    private String role;

    private List<Authority> authorities = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) return Collections.emptyList();

        return authorities.stream()
                .map(role -> (GrantedAuthority) role::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}