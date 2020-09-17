package co.rodnan.restaurant.application.action;

import co.rodnan.restaurant.application.exception.RestaurantNotFoundException;
import co.rodnan.restaurant.application.port.in.DailyMenuUseCase;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class DailyMenuAction implements DailyMenuUseCase {

    private final Instance<RestaurantPort> restaurantPorts;

    private final Map<String, RestaurantPort> restaurantPortMap = new HashMap<>();

    @PostConstruct
    public void init() {
        restaurantPorts.forEach(restaurantPort -> restaurantPortMap.put(restaurantPort.getRestaurantInfo().getIdentifier(), restaurantPort));
    }

    @Override
    public MenuInformation getDailyMenuByRestaurantIdentifier(String identifier) {
        log.info("Finding daily menu by restaurant identifier:[{}]", identifier);
        return Optional.ofNullable(restaurantPortMap.get(identifier))
                .orElseThrow(() -> getRestaurantNotFoundException(identifier))
                .getDailyMenu();
    }

    private RestaurantNotFoundException getRestaurantNotFoundException(String identifier) {
        return new RestaurantNotFoundException("No restaurant with identifier: " + identifier);
    }

}
