package co.rodnan.restaurant.adapter.out.web.flaska;

import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import co.rodnan.restaurant.domain.RestaurantInformation;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

/**
 *
 */
@ApplicationScoped
@RequiredArgsConstructor
public class FlaksaRestaurantPort implements RestaurantPort {

    private final FlaskaMenuParser flaskaMenuParser;

    @Override
    public MenuInformation getDailyMenu() {
        FlaskaDailyMenu flaskaDailyMenu = flaskaMenuParser.getDailyMenuAtDay(LocalDate.now().getDayOfWeek());
        List<MenuItem> menuItems = List.of(
                MenuItem.createSoup(flaskaDailyMenu.getSoup()),
                MenuItem.createMainCourse(flaskaDailyMenu.getMainCourse())
        );
        return MenuInformation.builder()
                .price(flaskaDailyMenu.getPrice())
                .menuItems(menuItems)
                .build();
    }

    @Override
    public RestaurantInformation getRestaurantInfo() {
        return RestaurantInformation.builder()
                .name("Flaska vendéglő")
                .identifier("flaska")
                .url("http://flaska.hu/")
                .logo("http://flaska.hu/wp-content/uploads/2015/06/cropped-header1.jpg")
                .build();
    }
}
