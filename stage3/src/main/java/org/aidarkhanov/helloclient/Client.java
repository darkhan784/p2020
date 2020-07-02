package org.aidarkhanov.helloclient;

import org.aidarkhanov.helloservice.Greeting;
import org.osgi.service.component.annotations.*;

@Component(
        immediate = true
)
public class Client {
    @Reference(
            service = Greeting.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.STATIC
    )
    public Greeting greeting;

    @Activate
    protected void onActivate() {
        System.out.println("Stage 3. Client activated");
        greeting.sayHello();
    }


}