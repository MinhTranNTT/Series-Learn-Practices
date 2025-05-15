package org.demo.demofeature.service.demoScope;

import org.demo.demofeature.DemoFeatureApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentTest {

    @Test
    public void testPrototypeBean() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoFeatureApplication.class);
        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
        System.out.println(prototypeBean1);
        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
        System.out.println(prototypeBean2);
        System.out.println(prototypeBean1 == prototypeBean2);
        context.close();
    }

    @Test
    public void testSingletonBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoFeatureApplication.class);
        SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
        System.out.println(singletonBean1);
        SingletonBean singletonBean2 = context.getBean(SingletonBean.class);
        System.out.println(singletonBean2);
        System.out.println(singletonBean1 == singletonBean2);
    }

    @Test
    public void testInjectBeanPrototypeIntoSingletonBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoFeatureApplication.class);
        SingletonBean1 singletonBean1 = context.getBean(SingletonBean1.class);
        for (int i = 0; i < 5; i++) {
            singletonBean1.callProtypeBeanPrint();
        }
    }
}