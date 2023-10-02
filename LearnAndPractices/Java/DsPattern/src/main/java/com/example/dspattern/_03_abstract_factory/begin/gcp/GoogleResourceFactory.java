package com.example.dspattern._03_abstract_factory.begin.gcp;

import com.example.dspattern._03_abstract_factory.begin.Instance;
import com.example.dspattern._03_abstract_factory.begin.ResourceFactory;
import com.example.dspattern._03_abstract_factory.begin.Storage;

public class GoogleResourceFactory implements ResourceFactory {
    @Override
    public Instance createInstance(Instance.Capacity capacity) {
        return new GoogleComputeEngineInstance(capacity);
    }

    @Override
    public Storage createStorage(int capMib) {
        return new GoogleCloudStorage(capMib);
    }
}
