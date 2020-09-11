package co.rodnan.restaurant.adapter.out.web.beerandwurst;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "beer-and-wurst-client")
public interface BeerAndWurstClient {

    @GET
    @Path("/api/menu")
    @Produces(MediaType.APPLICATION_JSON)
    BeerAndWurstMenuResponse getMenu();

}
