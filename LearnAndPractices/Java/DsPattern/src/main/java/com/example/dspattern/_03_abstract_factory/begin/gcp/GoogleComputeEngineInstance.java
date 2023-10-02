package com.example.dspattern._03_abstract_factory.begin.gcp;

import com.example.dspattern._03_abstract_factory.begin.Instance;
import com.example.dspattern._03_abstract_factory.begin.Storage;

public class GoogleComputeEngineInstance implements Instance {
    public GoogleComputeEngineInstance(Capacity capacity) {
        System.out.println("Created Google Compute Engine instance");
    }

    @Override
    public void start() {
        System.out.println("Compute engine instance started");
    }

    @Override
    public void stop() {
        System.out.println("Compute engine instance stopped");
    }

    @Override
    public void attachStorage(Storage storage) {
        System.out.println("Attached "+storage+" to Compute engine instance");
    }
}
