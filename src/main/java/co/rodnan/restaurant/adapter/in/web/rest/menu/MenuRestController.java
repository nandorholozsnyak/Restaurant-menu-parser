package co.rodnan.restaurant.adapter.in.web.rest.menu;

import co.rodnan.restaurant.adapter.in.web.domain.DailyMenuResponse;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/restaurants/{identifier}/daily-menus")
public interface MenuRestController {

    @Operation(summary = "Returns the daily menu for the given restaurant")
    @Tag(description = "Menu operations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    DailyMenuResponse getDailyMenu(@PathParam("identifier") String identifier);
}
