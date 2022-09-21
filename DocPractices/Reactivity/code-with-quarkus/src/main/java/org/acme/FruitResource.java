package org.acme;

import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/fruit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FruitResource {

    @Inject
    FruitService fruitService;

    @GET
    public Uni<List<Fruit>> get(){
        return fruitService.getAllFruits();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getSingle(Long id){

       /* return fruitService.getSingle(id)
                .onItem().transform( fruit -> fruit != null ? Response.ok() : Response.status(Response.Status.NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build); */

        return fruitService.getSingle(id)
                .onItem().ifNotNull().transform( fruit -> Response.ok().entity(fruit).build() )
                .onItem().ifNull().continueWith( Response.ok().status(Response.Status.NOT_FOUND).entity("Fruit not found").build());
    }

    @POST
    @ReactiveTransactional
    public Uni<Response> create(Fruit fruit, @Context UriInfo uriInfo){
        return fruitService.createFruit(fruit)
                .onItem().transform(h -> uriInfo.getAbsolutePathBuilder().path(Long.toString(h.id)).build())
                .onItem().transform(uri -> Response.created(uri).entity("Fruit created successfully!"))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @PUT
    @ReactiveTransactional
    @Path("{id}")
    public Uni<Response> update(Long id, Fruit fruit){
        return fruitService.updateFruit(id, fruit)
                .onItem().ifNotNull().transform( fr -> Response.ok().entity(fr).build() )
                .onItem().ifNull().continueWith( Response.ok().status(Response.Status.NOT_FOUND).entity("Fruit not found").build());
    }

    @DELETE
    @ReactiveTransactional
    @Path("{id}")
    public Uni<Response> delete( Long id ) {
        return fruitService.deleteFruit(id)
                .onItem().transform( bol ->
                        bol ? Response.ok().entity("Fruit deleted")
                                : Response.ok().entity("Fruit not found").status(Response.Status.NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }


}
