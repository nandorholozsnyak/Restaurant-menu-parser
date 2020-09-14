package co.rodnan.restaurant.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
@RequiredArgsConstructor
public class Restaurant {

    String name;
    String identifier;
    List<MenuItem> menuItems;
    BigDecimal menuPrice;
    RestaurantProcessStatus status;
}
