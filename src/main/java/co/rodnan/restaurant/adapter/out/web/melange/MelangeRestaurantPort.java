package co.rodnan.restaurant.adapter.out.web.melange;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import co.rodnan.restaurant.domain.RestaurantInformation;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class MelangeRestaurantPort extends HtmlBasedParser implements RestaurantPort {

    private static final String URL = "http://www.melangekavehaz.hu";

    private final MelangeDailyMenuParser melangeDailyMenuParser;

    @Override
    public MenuInformation getDailyMenu() {
        MelangeDailyMenu dailyMenu = melangeDailyMenuParser.getDailyMenu();
        List<MenuItem> menuItems = List.of(
                MenuItem.createSoup(dailyMenu.getSoup()),
                MenuItem.createMainCourseA(dailyMenu.getCourseA()),
                MenuItem.createMainCourseB(dailyMenu.getCourseB())
        );
        return MenuInformation.builder()
                .day(dailyMenu.getDay())
                .price(dailyMenu.getPrice())
                .menuItems(menuItems)
                .build();
    }

    @Override
    public RestaurantInformation getRestaurantInfo() {
        return RestaurantInformation.builder()
                .name("Melange Kávéház")
                .identifier("melange")
                .url(URL)
                .build();
    }

}
