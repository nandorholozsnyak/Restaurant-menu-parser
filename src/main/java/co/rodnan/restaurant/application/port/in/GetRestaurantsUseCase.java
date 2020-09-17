package co.rodnan.restaurant.application.port.in;

import co.rodnan.restaurant.domain.Restaurant;

import java.util.List;

public interface GetRestaurantsUseCase {

    /**
     * @return
     */
    List<Restaurant> getRestaurants();

    /**
     * @param identifier
     * @return
     */
    Restaurant getRestaurantByIdentifier(String identifier);
}
