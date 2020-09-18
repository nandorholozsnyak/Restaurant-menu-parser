package co.rodnan.restaurant.adapter.out.web.flaska;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@Builder
@RequiredArgsConstructor
public class FlaskaMenu {

    List<FlaskaDailyMenu> dailyMenus;

}
