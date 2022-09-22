package org.acme.fruit;

import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FruitService {

    public Uni<List<Fruit>> getAllFruits() {
        return Fruit.listAll(Sort.by("name"));
    }

    public Uni<Fruit> getSingle(Long id) {
        return Fruit.findById(id);
    }

    public Uni<Fruit> createFruit(Fruit fruit) {
        return fruit.persist();
    }

    public Uni<Fruit> updateFruit(Long id, Fruit fruit) {
        return Fruit.<Fruit>findById(id)
                .onItem().ifNotNull().transform(
                        fruitRetrieved ->  {
                            fruitRetrieved.name  = fruit.name;
                            return fruitRetrieved;
                        }
                );
    }

    public Uni<Boolean> deleteFruit(Long id) {
        return Fruit.deleteById(id);
    }



}
