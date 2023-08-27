package com.dragon.it.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.it.pojo.Hotel;
import com.dragon.it.pojo.PageResult;
import com.dragon.it.pojo.RequestParams;

public interface IHotelService extends IService<Hotel> {
    PageResult search(RequestParams params);
}
