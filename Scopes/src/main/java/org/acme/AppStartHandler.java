package org.acme;

import io.quarkus.runtime.StartupEvent;
import org.acme.ApplicationScoped.AppService;
import org.acme.DependentScoped.DependentService;
import org.acme.Singleton.SingletonService;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/*
Application wide scopes
- ApplicationScoped
- Singleton
Tienen estado compartido ya que solo se instancian una unica vez

Not wide scope
- Dependent
No tiene estado compartido, se intancia varias veces

Lazy
- ApplicationScoped
- RequestScoped
- SessionScoped

 */

public class AppStartHandler {

    @Inject
    AppService serviceOne;
    @Inject
    AppService serviceTwo;

    @Inject
    SingletonService serviceThree; //El servicio con el scope de singleton va a ser inicializado una vez se realice la inyección

    @Inject
    DependentService serviceFour; //Dependent scope para el bean
    @Inject
    DependentService serviceFive; //Dependent scope para el bean

    void onApplicationStart(@Observes StartupEvent e){

        System.out.println("App started!!!!!");

        //es lazy para ApplicationScope asi que solo va a ser instanciado si es llamado por primera vez
        System.out.println(serviceOne.getAndIncrementCounter());
        System.out.println(serviceTwo.getAndIncrementCounter());

        //Prueba para DependentScope, este no es lazy puesto que se inicializa sin ser llamado
        System.out.println(serviceFour.getAndIncrementNumber());
        System.out.println(serviceFive.getAndIncrementNumber());

    }


}
