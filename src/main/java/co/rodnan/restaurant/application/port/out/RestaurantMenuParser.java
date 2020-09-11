package co.rodnan.restaurant.application.port.out;

import co.rodnan.restaurant.domain.MenuInformation;

/**
 *
 */
public interface RestaurantMenuParser {

    /**
     * @return
     */
    MenuInformation parseMenu();

    /**
     * @return
     */
    String getRestaurantName();

}
