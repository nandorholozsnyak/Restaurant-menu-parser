package co.rodnan.restaurant.application.port.in;

import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;

import java.util.List;

/**
 *
 */
public interface DailyMenuUseCase {

    /**
     * @return
     */
    MenuInformation getDailyMenuByRestaurantIdentifier(String identifier);
}
