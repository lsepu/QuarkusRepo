package org.acme.ApplicationScoped;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppService {

    private int counter;

    @PostConstruct
    void onConstructed(){
        System.out.println("@Application scoped constructed");
    }

    public int getAndIncrementCounter(){
        return counter++;
    }


}
