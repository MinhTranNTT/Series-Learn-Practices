package com.example.mybatisday04.mapper;

import com.example.mybatisday04.pojo.Shop;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@MapperScan("com.example.mybatisday04.mapper")
@SpringBootTest
class ShopMapperTest {
    @Autowired private ShopMapper shopMapper;

    @Test
    public void testContextLoads() {
        // A & B same read version data
        Shop shopA = shopMapper.selectById(1L);
        Shop shopB = shopMapper.selectById(1L);

        shopB.setPrice(9000);
        int resultB = shopMapper.updateById(shopB);
        if (resultB == 1) {
            System.out.println("B success modify price");
        } else {
            System.out.println("B not success modify price");
        }

        shopA.setPrice(7000);
        int resultA = shopMapper.updateById(shopA);
        if (resultA == 1) {
            System.out.println("A success modify price");
        } else {
            System.out.println("A not success modify price");
        }

        System.out.println(shopMapper.selectById(1L));
    }
}