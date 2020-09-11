package co.rodnan.restaurant.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class Restaurant {

    String name;
    List<MenuItem> menuItems;
    BigDecimal menuPrice;
    RestaurantProcessStatus status;
}
