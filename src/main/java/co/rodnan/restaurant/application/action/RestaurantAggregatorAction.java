package co.rodnan.restaurant.application.action;

import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.Restaurant;
import co.rodnan.restaurant.domain.RestaurantListResponse;
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
public class RestaurantAggregatorAction {

    private final Instance<RestaurantPort> menuParsers;

    //TODO Make the process async with CompletableFuture or antyhing else.
    public RestaurantListResponse getRestaurantMenus() {
        List<Restaurant> restaurants = menuParsers.stream()
                .parallel()
                .map(this::createRestaurant)
                .collect(Collectors.toList());
        return new RestaurantListResponse(restaurants);
    }

    private Restaurant createRestaurant(RestaurantPort restaurantPort) {
        RestaurantProcessStatus processStatus = RestaurantProcessStatus.OK;
        MenuInformation menuInformation = MenuInformation.EMPTY;
        try {
            menuInformation = restaurantPort.getDailyMenu();
        } catch (Exception e) {
            processStatus = RestaurantProcessStatus.FAILED;
            log.warn("Error during parsing menu of the restaurant:" + restaurantPort.getRestaurantName(), e);
        }
        return Restaurant.builder()
                .name(restaurantPort.getRestaurantName())
                .identifier(restaurantPort.getRestaurantId())
                .menuItems(menuInformation.getMenuItems())
                .menuPrice(menuInformation.getPrice())
                .status(processStatus)
                .build();
    }
}
