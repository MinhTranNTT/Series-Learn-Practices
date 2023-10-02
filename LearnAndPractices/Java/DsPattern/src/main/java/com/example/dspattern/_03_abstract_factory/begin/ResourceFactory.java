package com.example.dspattern._03_abstract_factory.begin;

public interface ResourceFactory {
    Instance createInstance(Instance.Capacity capacity);
    Storage createStorage(int capMib);
}
