package co.rodnan.restaurant.adapter.in.web.domain;

import lombok.Value;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Value
@Schema(description = "Model containing requested restaurant")
@Description("Model containing requested restaurant")
public class RestaurantResponse {

    @Schema(description = "Requested restaurant")
    @Description("Requested restaurant")
    RestaurantInfoType restaurant;

}
