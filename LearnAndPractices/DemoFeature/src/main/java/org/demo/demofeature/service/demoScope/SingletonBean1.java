package org.demo.demofeature.service.demoScope;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@AllArgsConstructor
public class SingletonBean1 {
    private final PrototypeBean1 prototypeBean;

    public void callProtypeBeanPrint() {
        prototypeBean.printCurrentObj();
    }
}
