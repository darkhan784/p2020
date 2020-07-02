package org.aidarkhanov.helloservice;

import org.aidarkhanov.helloservice.impl.GreetingImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ServiceActivator implements BundleActivator {
    public void start(BundleContext ctx) {
        Greeting greet = new GreetingImpl("OSGI World");
        greet.sayHello();
        ctx.registerService(Greeting.class.getName(),
                new GreetingImpl("service"), null);
    }

    public void stop(BundleContext ctx) {
    }
}
