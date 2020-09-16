package co.rodnan.restaurant.application.port.out;

import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.RestaurantInformation;

/**
 *
 */
public interface RestaurantPort {

    /**
     * Parses the menu of the actual resturant and returns it in a format which can be handled by the application.
     *
     * @return
     */
    MenuInformation getDailyMenu();

    /**
     * @return
     */
    RestaurantInformation getRestaurantInfo();

}
