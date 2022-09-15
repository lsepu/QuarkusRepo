package org.acme.RequestScoped;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.text.NumberFormat;

@Path("/calculator/addition")
public class CalculatorAdditionResource {

    @Inject
    RequestUserContext requestLocale;

/*    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String plus(@QueryParam("a") int a, @QueryParam("b") int b) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(requestLocale.getLocale());
        return numberFormat.format(a + b);
    }*/

}
