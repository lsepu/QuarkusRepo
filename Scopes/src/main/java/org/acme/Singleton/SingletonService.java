package org.acme.Singleton;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class SingletonService {

    @PostConstruct
    void OnConstructed(){
        System.out.println("@Singleton scope initialized!!");
    }

}
