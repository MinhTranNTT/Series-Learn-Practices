package com.example.dspattern._03_abstract_factory.begin.aws;

import com.example.dspattern._03_abstract_factory.begin.Storage;

public class S3Storage implements Storage {
    public S3Storage(int capacityInMib) {
        System.out.println("Allocated "+capacityInMib+" on S3");
    }

    @Override
    public String getId() {
        return "S3-1";
    }

    @Override
    public String toString() {
        return "S3 Storage";
    }
}
