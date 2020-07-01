package org.itmo.helloservice.impl;

import org.itmo.helloservice.Greeting;
import org.osgi.service.component.annotations.Component;

@Component(
        service = Greeting.class,
        immediate = true
)
public class GreetingImpl implements Greeting {

    public void sayHello() {
        System.out.println("Hello,OSGi(stage 3)!");
    }
}