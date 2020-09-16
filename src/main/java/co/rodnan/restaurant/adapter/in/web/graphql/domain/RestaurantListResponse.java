package co.rodnan.restaurant.adapter.in.web.graphql.domain;

import lombok.Value;
import org.eclipse.microprofile.graphql.Description;

import java.util.List;

@Value
public class RestaurantListResponse {

    @Description("List of restaurants")
    List<RestaurantInfoType> restaurants;

}
