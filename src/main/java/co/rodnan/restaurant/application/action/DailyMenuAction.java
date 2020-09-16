package co.rodnan.restaurant.application.action;

import co.rodnan.restaurant.application.port.in.DailyMenuUseCase;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import java.util.List;

/**
 *
 */
@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class DailyMenuAction implements DailyMenuUseCase {

    private final Instance<RestaurantPort> restaurantPorts;

    public List<MenuItem> getDailyMenuByRestaurantIdentifier(String identifier) {

        return restaurantPorts.stream()
                .filter(restaurantPort -> identifier.equals(restaurantPort.getRestaurantInfo().getIdentifier()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No restaurant with identifier: " + identifier))
                .getDailyMenu()
                .getMenuItems();
    }

}
