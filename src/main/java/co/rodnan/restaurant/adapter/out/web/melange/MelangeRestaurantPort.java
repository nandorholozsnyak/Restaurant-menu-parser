package co.rodnan.restaurant.adapter.out.web.melange;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class MelangeRestaurantPort extends HtmlBasedParser implements RestaurantPort {

    private static final String URL = "http://www.melangekavehaz.hu/menu/heti_menu_ajanlatunk_{period}";

    private static final String COLUMN_SELECTOR = "#oszlop-{index}";

    private final MelangeDailyMenuParser melangeDailyMenuParser;

    @Override
    public MenuInformation getDailyMenu() {
        MelangeDailyMenu dailyMenu = melangeDailyMenuParser.getDailyMenu();
        return new MenuInformation(dailyMenu.getDay(), dailyMenu.getPrice(),
                List.of(
                        MenuItem.createSoup(dailyMenu.getSoup()),
                        MenuItem.createMainCourseA(dailyMenu.getCourseA()),
                        MenuItem.createMainCourseB(dailyMenu.getCourseB())
                )
        );
    }

    @Override
    public String getRestaurantName() {
        return "Melange Kávéház";
    }

    @Override
    public String getRestaurantId() {
        return "melange";
    }

}
