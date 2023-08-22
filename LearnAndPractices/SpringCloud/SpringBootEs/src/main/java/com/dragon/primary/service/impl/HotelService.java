package com.dragon.primary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.primary.mapper.HotelMapper;
import com.dragon.primary.pojo.Hotel;
import com.dragon.primary.service.IHotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

}
