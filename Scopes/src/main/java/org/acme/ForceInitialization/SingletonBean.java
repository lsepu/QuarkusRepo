package org.acme.ForceInitialization;

import io.quarkus.runtime.Startup;

import javax.enterprise.context.Initialized;
import javax.inject.Singleton;

@Singleton
@Startup
public class SingletonBean {

    /* by registering our bean as an observer for specific events, we can force the instantiation.
    By adding an observer method for the StartupEvent, the application start will initialize the bean at least once
    We can also use the @Startup annotation or the observer method if it is RequestScope or SessionScope
     */


}
