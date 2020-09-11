package co.rodnan.restaurant.application.action;

import co.rodnan.restaurant.application.port.out.RestaurantMenuParser;
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

    private final Instance<RestaurantMenuParser> menuParsers;

    public RestaurantListResponse getRestaurantMenus() {
        List<Restaurant> restaurants = new LinkedList<>();
        for (RestaurantMenuParser restaurantMenuParser : menuParsers) {
            Restaurant restaurant = createRestaurant(restaurantMenuParser);
            restaurants.add(restaurant);
        }
        return new RestaurantListResponse(restaurants);
    }

    private Restaurant createRestaurant(RestaurantMenuParser restaurantMenuParser) {
        RestaurantProcessStatus processStatus = RestaurantProcessStatus.OK;
        MenuInformation menuInformation = MenuInformation.EMPTY;
        try {
            menuInformation = restaurantMenuParser.parseMenu();
        } catch (Exception e) {
            processStatus = RestaurantProcessStatus.FAILED;
            log.warn("Error during parsing menu of the restaurant:" + restaurantMenuParser.getRestaurantName(), e);
        }
        return new Restaurant(restaurantMenuParser.getRestaurantName(), menuInformation.getMenuItems(), menuInformation.getPrice(), processStatus);
    }
}
