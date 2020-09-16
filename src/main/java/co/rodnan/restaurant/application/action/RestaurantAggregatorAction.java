package co.rodnan.restaurant.application.action;

import co.rodnan.restaurant.application.port.in.GetRestaurantsUseCase;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.Restaurant;
import co.rodnan.restaurant.domain.RestaurantInformation;
import co.rodnan.restaurant.domain.RestaurantProcessStatus;
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
        return restaurantPorts.stream()
                .parallel()
                .map(this::createRestaurant)
                .collect(Collectors.toList());
    }

    private Restaurant createRestaurant(RestaurantPort restaurantPort) {
        RestaurantInformation restaurantInfo = restaurantPort.getRestaurantInfo();
        RestaurantProcessStatus processStatus = RestaurantProcessStatus.OK;
        return Restaurant.builder()
                .name(restaurantInfo.getName())
                .identifier(restaurantInfo.getIdentifier())
                //.menuItems(menuInformation.getMenuItems())
                //.menuPrice(menuInformation.getPrice())
                .status(processStatus)
                .build();
    }
}
