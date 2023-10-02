package com.example.dspattern._03_abstract_factory.begin.aws;

import com.example.dspattern._03_abstract_factory.begin.Instance;
import com.example.dspattern._03_abstract_factory.begin.ResourceFactory;
import com.example.dspattern._03_abstract_factory.begin.Storage;

public class AwsResourceFactory implements ResourceFactory {
    @Override
    public Instance createInstance(Instance.Capacity capacity) {
        return new Ec2Instance(capacity);
    }

    @Override
    public Storage createStorage(int capMib) {
        return new S3Storage(capMib);
    }
}
