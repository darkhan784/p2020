package org.aidarkhanov.command;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

public class CommandActivator implements BundleActivator {
    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext ctx) throws Exception {
        CommandActivator.context = ctx;
        Hashtable props = new Hashtable();
        props.put("osgi.command.scope", "practice");
        props.put("osgi.command.function", "hello");
        context.registerService(Command.class.getName(), new Command(context), props);
    }

    public void stop(BundleContext ctx) throws Exception {
        CommandActivator.context = null;
    }

}
