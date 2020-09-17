package co.rodnan.restaurant.adapter.in.web.rest.restaurant;

import co.rodnan.restaurant.adapter.in.web.domain.RestaurantInfoType;
import co.rodnan.restaurant.adapter.in.web.domain.RestaurantListResponse;
import co.rodnan.restaurant.adapter.in.web.domain.RestaurantResponse;
import co.rodnan.restaurant.adapter.in.web.mapper.RestaurantMapper;
import co.rodnan.restaurant.application.port.in.GetRestaurantsUseCase;
import co.rodnan.restaurant.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RestaurantRestControllerImpl implements RestaurantRestController {

    private final RestaurantMapper restaurantMapper;
    private final GetRestaurantsUseCase getRestaurantsUseCase;

    @Override
    public RestaurantListResponse getRestaurants() {
        log.trace(">> getRestaurantMenus()");
        List<RestaurantInfoType> restaurantInfoTypes = getRestaurantsUseCase.getRestaurants()
                .stream()
                .map(this::mapToType)
                .collect(Collectors.toList());
        return new RestaurantListResponse(restaurantInfoTypes);
    }

    @Override
    public RestaurantResponse getRestaurantByIdentifier(String identifier) {
        log.trace(">> getRestaurantByIdentifier(identifier:[{}])", identifier);
        Restaurant restaurant = getRestaurantsUseCase.getRestaurantByIdentifier(identifier);
        return new RestaurantResponse(mapToType(restaurant));
    }

    private RestaurantInfoType mapToType(Restaurant restaurant) {
        return restaurantMapper.mapToInfoType(restaurant);
    }
}
