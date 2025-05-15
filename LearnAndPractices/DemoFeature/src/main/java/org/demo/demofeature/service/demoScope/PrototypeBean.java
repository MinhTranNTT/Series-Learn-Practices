package org.demo.demofeature.service.demoScope;

public class PrototypeBean {
    public PrototypeBean() {
        System.out.println("Constructor PrototypeBean");
    }

    public void init() {
        System.out.println("init PrototypeBean");
    }

    public void destroy() {
        System.out.println("Cancel PrototypeBean");
    }
}
