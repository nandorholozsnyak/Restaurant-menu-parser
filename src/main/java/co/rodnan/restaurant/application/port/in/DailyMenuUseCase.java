package co.rodnan.restaurant.application.port.in;

import co.rodnan.restaurant.domain.MenuItem;

import java.util.List;

/**
 *
 */
public interface DailyMenuUseCase {

    /**
     * @return
     */
    List<MenuItem> getDailyMenuByRestaurantIdentifier(String identifier);
}
