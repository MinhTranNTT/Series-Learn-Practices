package com.dragon.it.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.it.pojo.Hotel;
import com.dragon.it.pojo.PageResult;
import com.dragon.it.pojo.RequestParams;

import java.util.List;
import java.util.Map;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params);

    Map<String, List<String>> getFilters(RequestParams params);

    void deleteById(Long id);

    void insertById(Long id);
}
