package com.example.dspattern._03_abstract_factory.begin;

public interface Instance {
    enum Capacity{ micro, small, large };
    void start();
    void stop();
    void attachStorage(Storage storage);
}
