package co.rodnan.restaurant.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
@RequiredArgsConstructor
public class MenuInformation {

    public static final MenuInformation EMPTY = new MenuInformation(BigDecimal.ZERO, List.of());

    BigDecimal price;
    List<MenuItem> menuItems;

}
