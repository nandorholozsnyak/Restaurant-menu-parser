package co.rodnan.restaurant.application.port.out;

import co.rodnan.restaurant.domain.MenuInformation;

/**
 *
 */
public interface RestaurantPort {

    /**
     * Parses the menu of the actual resturant and returns it in a format which can be handled by the application.
     *
     * @return
     */
    MenuInformation parseMenu();

    /**
     * Returns the name of the restaurant.
     *
     * @return name of the restaurant.
     */
    String getRestaurantName();

    /**
     * Returns the custom identifier of the restaurant.
     *
     * @return identifier of the restaurant.
     */
    String getRestaurantId();

}
