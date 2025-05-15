package org.demo.demofeature.service.demoScope;

public class SingletonBean {
    public SingletonBean() {
        System.out.println("Constructor SingletonBean");
    }

    public void init() {
        System.out.println("Init SingletonBean");
    }

    public void destroy() {
        System.out.println("Destroy SingletonBean");
    }
}
