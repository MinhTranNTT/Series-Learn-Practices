package com.example.dspattern._04_Singleton.begin;

public class LazyRegistryWithDCL {
    private LazyRegistryWithDCL() {
    }

    // Volatile means that we shouldn't use cached version of this variable.
    // Every access will refer to main memory instead of cache.
    private static volatile LazyRegistryWithDCL INSTANCE;

    public static LazyRegistryWithDCL getInstance() {
        if (INSTANCE == null) {
            synchronized (LazyRegistryWithDCL.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazyRegistryWithDCL();
                }
            }
        }
        return INSTANCE;
    }
}
