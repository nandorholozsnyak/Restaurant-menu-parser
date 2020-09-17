package co.rodnan.restaurant.adapter.in.web.rest.restaurant;

import co.rodnan.restaurant.adapter.in.web.domain.RestaurantListResponse;
import co.rodnan.restaurant.adapter.in.web.domain.RestaurantResponse;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/restaurants")
public interface RestaurantRestController {

    @Operation(summary = "Returns all restraurants")
    @Tag(name = "Restaurant operations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    RestaurantListResponse getRestaurants();

    @Operation(summary = "Returns restaurant by its identifier")
    @Tag(name = "Restaurant operations")
    @GET
    @Path("/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    RestaurantResponse getRestaurantByIdentifier(@PathParam("identifier") String identifier);

}
