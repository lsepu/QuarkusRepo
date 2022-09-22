package org.acme.book;

import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService bookService;

    @GET
    public Uni<Response> getAllBooks(){
        return bookService.getAllBooks()
                .map(h -> Response.ok().entity(h))
                .map(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/author/{name}")
    public Uni<Response> findByAuthor(String name){
        return bookService.findByAuthor(name)
                .onItem().ifNotNull().transform(h -> Response.ok().entity(h).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @ReactiveTransactional
    public Uni<Response> addBook(@Valid BookDTO bookDTO){
        return bookService.addBook(bookDTO)
                .onItem().ifNotNull().transform(h -> Response.ok().entity(h).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.BAD_REQUEST).build());
    }


}
