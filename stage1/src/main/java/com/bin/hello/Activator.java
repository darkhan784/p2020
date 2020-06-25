package com.bin.hello;

import com.bin.hello.impl.GreetingImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        IGreeting hello = new GreetingImpl("OSGI World");
        hello.sayHello();
    }

    public void stop(BundleContext context) {

    }

}