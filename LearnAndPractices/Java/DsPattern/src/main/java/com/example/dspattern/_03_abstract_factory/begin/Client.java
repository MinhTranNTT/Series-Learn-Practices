package com.example.dspattern._03_abstract_factory.begin;

import com.example.dspattern._03_abstract_factory.begin.aws.AwsResourceFactory;
import com.example.dspattern._03_abstract_factory.begin.gcp.GoogleResourceFactory;

public class Client {
    private ResourceFactory resourceFactory;
    public Client(ResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
    }

    public Instance createServer(Instance.Capacity capacity, int storageMib) {
        Instance instance = resourceFactory.createInstance(capacity);
        Storage storage = resourceFactory.createStorage(storageMib);
        instance.attachStorage(storage);
        return instance;
    }

    public static void main(String[] args) {
        Client aws = new Client(new AwsResourceFactory());
        Instance i1 = aws.createServer(Instance.Capacity.micro, 20481);
        i1.start();
        i1.stop();

        System.out.println("---------------------------");

        Client gcp = new Client(new GoogleResourceFactory());
        Instance i2 = gcp.createServer(Instance.Capacity.large, 40800);
        i2.start();
        i2.stop();
    }

}
