package com.example.dspattern._03_abstract_factory.begin.gcp;

import com.example.dspattern._03_abstract_factory.begin.Storage;

public class GoogleCloudStorage implements Storage {
    public GoogleCloudStorage(int capacityInMib) {
        System.out.println("Allocated "+capacityInMib+" on Google Cloud Storage");
    }

    @Override
    public String getId() {
        return "gcpcs-1";
    }

    @Override
    public String toString() {
        return "Google cloud storage";
    }
}
