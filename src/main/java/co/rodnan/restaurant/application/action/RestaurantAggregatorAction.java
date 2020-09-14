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
import java.util.LinkedList;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class RestaurantAggregatorAction {

    private final Instance<RestaurantPort> menuParsers;

    public RestaurantListResponse getRestaurantMenus() {
        List<Restaurant> restaurants = new LinkedList<>();
        for (RestaurantPort restaurantPort : menuParsers) {
            Restaurant restaurant = createRestaurant(restaurantPort);
            restaurants.add(restaurant);
        }
        return new RestaurantListResponse(restaurants);
    }

    private Restaurant createRestaurant(RestaurantPort restaurantPort) {
        RestaurantProcessStatus processStatus = RestaurantProcessStatus.OK;
        MenuInformation menuInformation = MenuInformation.EMPTY;
        try {
            menuInformation = restaurantPort.parseMenu();
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
