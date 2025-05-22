package org.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@TableName("authorities")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    private int id;
    private int customerId;
    private List<String> roles;
}
