package co.rodnan.restaurant.adapter.out.web.melange;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Value
@Builder
@RequiredArgsConstructor
public class MelangeDailyMenu {

    BigDecimal price;
    DayOfWeek day;
    String soup;
    String courseA;
    String courseB;

}
