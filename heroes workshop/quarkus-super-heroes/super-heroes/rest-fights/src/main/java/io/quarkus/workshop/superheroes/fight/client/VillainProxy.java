package io.quarkus.workshop.superheroes.fight.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/api/villains")
@Produces(APPLICATION_JSON)
@RegisterRestClient
public interface VillainProxy {

    @GET
    @Path("/random")
    Villain findRandomVillain();
}
