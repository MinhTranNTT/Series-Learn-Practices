package com.example.dspattern._04_Singleton.begin;

public class EagerRegistry {
    private EagerRegistry() {
    }

    private static final EagerRegistry INSTANCE = new EagerRegistry();

    // singleton getter
    public static EagerRegistry getInstance() {
        return INSTANCE;
    }
}
