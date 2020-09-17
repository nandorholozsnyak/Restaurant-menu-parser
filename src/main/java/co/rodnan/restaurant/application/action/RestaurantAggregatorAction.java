package co.rodnan.restaurant.application.action;

import co.rodnan.restaurant.application.exception.RestaurantNotFoundException;
import co.rodnan.restaurant.application.port.in.GetRestaurantsUseCase;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.Restaurant;
import co.rodnan.restaurant.domain.RestaurantInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RestaurantAggregatorAction implements GetRestaurantsUseCase {

    private final Instance<RestaurantPort> restaurantPorts;

    //TODO Make the process async with CompletableFuture or antyhing else.
    @Override
    public List<Restaurant> getRestaurants() {
        log.info("Finding all restaurants");
        return restaurantPorts.stream()
                .parallel()
                .map(this::createRestaurant)
                .collect(Collectors.toList());
    }

    @Override
    public Restaurant getRestaurantByIdentifier(String identifier) {
        log.info("Finding restaurant by it's identifier:[{}]", identifier);
        return createRestaurant(restaurantPorts.stream()
                .filter(restaurantPort -> identifier.equals(restaurantPort.getRestaurantInfo().getIdentifier()))
                .findFirst()
                .orElseThrow(() -> getRestaurantNotFoundException(identifier)));
    }

    private RestaurantNotFoundException getRestaurantNotFoundException(String identifier) {
        return new RestaurantNotFoundException("No restaurant with identifier: " + identifier);
    }

    private Restaurant createRestaurant(RestaurantPort restaurantPort) {
        RestaurantInformation restaurantInfo = restaurantPort.getRestaurantInfo();
        return Restaurant.builder()
                .name(restaurantInfo.getName())
                .identifier(restaurantInfo.getIdentifier())
                .build();
    }
}
