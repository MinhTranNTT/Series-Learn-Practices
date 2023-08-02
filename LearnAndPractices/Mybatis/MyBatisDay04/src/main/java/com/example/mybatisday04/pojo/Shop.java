package com.example.mybatisday04.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    private Long id;
    private String name;
    private Integer price;

    @Version
    private Integer version;
}
