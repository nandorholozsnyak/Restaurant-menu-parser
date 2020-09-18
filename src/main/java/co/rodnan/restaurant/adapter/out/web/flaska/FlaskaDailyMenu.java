package co.rodnan.restaurant.adapter.out.web.flaska;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Value
@Builder
@RequiredArgsConstructor
public class FlaskaDailyMenu {

    DayOfWeek day;
    BigDecimal price;
    String soup;
    String mainCourse;

}
