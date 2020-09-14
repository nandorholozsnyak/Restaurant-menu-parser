package co.rodnan.restaurant.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Value
@Builder
@RequiredArgsConstructor
public class MenuInformation {

    public static final MenuInformation EMPTY = new MenuInformation(LocalDate.now().getDayOfWeek(), BigDecimal.ZERO, List.of());

    DayOfWeek day;
    BigDecimal price;
    List<MenuItem> menuItems;

}
