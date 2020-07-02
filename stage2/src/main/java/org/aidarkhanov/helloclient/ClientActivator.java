package org.aidarkhanov.helloclient;

import org.aidarkhanov.helloservice.Greeting;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ClientActivator implements BundleActivator {
    public void start(BundleContext ctx) {
        ServiceReference ref =
                ctx.getServiceReference(Greeting.class.getName());
        ((Greeting) ctx.getService(ref)).sayHello();
    }
 
    public void stop(BundleContext ctx) {
    }
}