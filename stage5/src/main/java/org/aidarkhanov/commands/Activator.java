package org.aidarkhanov.commands;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

public class Activator implements BundleActivator {
    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext ctx) throws Exception {
        Activator.context = ctx;
        Hashtable props = new Hashtable();
        props.put("osgi.command.scope", "news");
        props.put("osgi.command.function", "stats");
        context.registerService(NewsTopWordsCommand.class.getName(), new NewsTopWordsCommand(), props);
    }


    public void stop(BundleContext ctx) throws Exception {
        Activator.context = null;
    }

}
