package co.rodnan.restaurant.adapter.in.web.graphql;

import co.rodnan.restaurant.adapter.in.web.domain.RestaurantInfoType;
import co.rodnan.restaurant.adapter.in.web.domain.RestaurantListResponse;
import co.rodnan.restaurant.adapter.in.web.domain.RestaurantResponse;
import co.rodnan.restaurant.adapter.in.web.mapper.RestaurantMapper;
import co.rodnan.restaurant.application.port.in.GetRestaurantsUseCase;
import co.rodnan.restaurant.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@GraphQLApi
@ApplicationScoped
@RequiredArgsConstructor
public class RestaurantResource {

    private final RestaurantMapper restaurantMapper;
    private final GetRestaurantsUseCase getRestaurantsUseCase;

    @Query("getRestaurants")
    @Description("Returns the list of the restaurants")
    public RestaurantListResponse getRestaurantMenus() {
        log.trace(">> getRestaurantMenus()");
        List<RestaurantInfoType> restaurantInfoTypes = getRestaurantsUseCase.getRestaurants()
                .stream()
                .map(this::mapToType)
                .collect(Collectors.toList());
        return new RestaurantListResponse(restaurantInfoTypes);
    }

    @Query("getRestaurantByIdentifier")
    @Description("Returns the requested restaurant by its identifier")
    public RestaurantResponse getRestaurantByIdentifier(@Name("identifier") String identifier) {
        log.trace(">> getRestaurantByIdentifier(identifier:[{}])", identifier);
        Restaurant restaurant = getRestaurantsUseCase.getRestaurantByIdentifier(identifier);
        return new RestaurantResponse(mapToType(restaurant));
    }

    private RestaurantInfoType mapToType(Restaurant restaurant) {
        return restaurantMapper.mapToInfoType(restaurant);
    }

}
