package com.dragon.it.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDoc {
    private Long id;
    private String name;
    private String address;
    private Integer price;
    private Integer score;
    private String brand;
    private String city;
    private String starName;
    private String business;
    private String location;
    private String pic;
    private Object distance;
    private boolean isAds;

    public static HotelDoc fromHotelDoc(Hotel hotel) {
        return HotelDoc.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .address(hotel.getAddress())
                .price(hotel.getPrice())
                .score(hotel.getScore())
                .brand(hotel.getBrand())
                .city(hotel.getCity())
                .starName(hotel.getStarName())
                .business(hotel.getBusiness())
                .location(hotel.getLatitude() + ", " + hotel.getLongitude())
                .pic(hotel.getPic()).build();
    }
}
