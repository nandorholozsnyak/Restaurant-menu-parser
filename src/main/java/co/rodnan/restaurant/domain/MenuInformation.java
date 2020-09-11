package co.rodnan.restaurant.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class MenuInformation {

    public static final MenuInformation EMPTY = new MenuInformation(BigDecimal.ZERO, List.of());

    BigDecimal price;
    List<MenuItem> menuItems;

}
