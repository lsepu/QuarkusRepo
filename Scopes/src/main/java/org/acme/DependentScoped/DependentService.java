package org.acme.DependentScoped;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

@Dependent
public class DependentService {

    int counter = 0;

    @PostConstruct
    void onConstructed(){
        System.out.println("@Dependent Scope initialized");
    }

    public int getAndIncrementNumber(){
        return counter++;
    }

}
