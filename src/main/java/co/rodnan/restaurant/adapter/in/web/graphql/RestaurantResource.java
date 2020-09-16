package co.rodnan.restaurant.adapter.in.web.graphql;

import co.rodnan.restaurant.adapter.in.web.graphql.domain.RestaurantInfoType;
import co.rodnan.restaurant.adapter.in.web.graphql.domain.RestaurantListResponse;
import co.rodnan.restaurant.application.port.in.GetRestaurantsUseCase;
import co.rodnan.restaurant.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@GraphQLApi
@ApplicationScoped
@RequiredArgsConstructor
public class RestaurantResource {

    private final GetRestaurantsUseCase getRestaurantsUseCase;

    @Query("getRestaurants")
    @Description("Returns the list of the restaurants")
    public RestaurantListResponse getRestaurantMenus() {
        List<RestaurantInfoType> restaurantInfoTypes = getRestaurantsUseCase.getRestaurants()
                .stream()
                .map(this::mapToType)
                .collect(Collectors.toList());
        return new RestaurantListResponse(restaurantInfoTypes);
    }

    private RestaurantInfoType mapToType(Restaurant restaurant) {
        return RestaurantInfoType.builder()
                .identifier(restaurant.getIdentifier())
                .name(restaurant.getName())
                .url(restaurant.getUrl())
                .logoUrl(restaurant.getLogoUrl())
                .status(restaurant.getStatus().name())
                .build();
    }

}
