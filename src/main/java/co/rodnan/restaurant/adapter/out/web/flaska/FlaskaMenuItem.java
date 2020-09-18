package co.rodnan.restaurant.adapter.out.web.flaska;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.DayOfWeek;

@Value
@Builder
@RequiredArgsConstructor
public class FlaskaMenuItem {

    String name;

}
