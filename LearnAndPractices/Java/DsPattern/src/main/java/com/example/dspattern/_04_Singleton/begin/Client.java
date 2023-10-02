package com.example.dspattern._04_Singleton.begin;

public class Client {
    public static void main(String[] args) {
        EagerRegistry eagerRegistry1 = EagerRegistry.getInstance();
        EagerRegistry eagerRegistry2 = EagerRegistry.getInstance();

        LazyRegistryWithDCL lazyRegistryWithDCL1 = LazyRegistryWithDCL.getInstance();
        LazyRegistryWithDCL lazyRegistryWithDCL2 = LazyRegistryWithDCL.getInstance();

        System.out.println(lazyRegistryWithDCL1);
        System.out.println(lazyRegistryWithDCL2);

    }
}
