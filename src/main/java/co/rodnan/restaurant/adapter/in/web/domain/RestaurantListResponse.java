package co.rodnan.restaurant.adapter.in.web.domain;

import lombok.Value;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Value
@Schema(description = "Model stores list of restaurants")
@Description("Model stores list of restaurants")
public class RestaurantListResponse {

    @Schema(description = "List of restaurants")
    @Description("List of restaurants")
    List<RestaurantInfoType> restaurants;

}
