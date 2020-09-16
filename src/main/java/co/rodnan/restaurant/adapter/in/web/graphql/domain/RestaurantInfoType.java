package co.rodnan.restaurant.adapter.in.web.graphql.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.eclipse.microprofile.graphql.Description;

@Value
@Builder
@RequiredArgsConstructor
@Description("Model stores information about the restaurant")
public class RestaurantInfoType {

    @Description("Name of the restaurant")
    String name;
    @Description("Identifier of the restaurant")
    String identifier;
    @Description("URL of the restaurant")
    String url;
    @Description("Logo of the restaurant, an URL")
    String logoUrl;
    @Description("Status of the restaurant")
    String status;
}
