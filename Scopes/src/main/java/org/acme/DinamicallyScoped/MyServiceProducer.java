package org.acme.DinamicallyScoped;

import io.quarkus.arc.DefaultBean;
import io.quarkus.arc.profile.IfBuildProfile;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

public class MyServiceProducer {

    /*
    We might find ourselves in the situation where we want to set the scope of a bean class at runtime dynamically.
    Or, we may want to use a class that comes from an external library as a bean. In both cases, we could not
    hard-code the scope annotation to the class. So-called producer methods (which must be annotated with javax.enterprise.inject.Produces)
    are a solution to this problem.
     */

    @Produces
    @ApplicationScoped
    @IfBuildProfile("dev")
    public MyService createApplicationScopedService() {
        return new MyService();
    }

    @Produces
    @RequestScoped
    @DefaultBean
    public MyService createRequestScopedService() {
        return new MyService();
    }


}
