package co.rodnan.restaurant.adapter.out.web.beerandwurst;

import co.rodnan.restaurant.application.port.out.RestaurantMenuParser;
import co.rodnan.restaurant.domain.CourseType;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BeerAndWurstRestaurantMenuParser implements RestaurantMenuParser {

    private final BeerAndWurstClient beerAndWurstClient;

    public BeerAndWurstRestaurantMenuParser(@RestClient BeerAndWurstClient beerAndWurstClient) {
        this.beerAndWurstClient = beerAndWurstClient;
    }

    @Override
    public MenuInformation parseMenu() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        BeerAndWurstMenuResponse menuResponse = beerAndWurstClient.getMenu();
        BeerAndWurstMenu wurstMenu = menuResponse.getMenuItems().stream()
                .filter(beerAndWurstMenu -> isDayToday(dayOfWeek, beerAndWurstMenu))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No menu for given day:" + dayOfWeek));
        List<MenuItem> menuItems = List.of(
                new MenuItem(wurstMenu.getSoup(), CourseType.SOUP),
                new MenuItem(wurstMenu.getMainCourse(), CourseType.MAIN_COURSE),
                new MenuItem(wurstMenu.getMainCourseB(), CourseType.MAIN_COURSE),
                new MenuItem(wurstMenu.getDessert(), CourseType.DESSERT));
        return new MenuInformation(new BigDecimal(menuResponse.getPrice()), menuItems);
    }

    private boolean isDayToday(DayOfWeek dayOfWeek, BeerAndWurstMenu beerAndWurstMenu) {
        return DayOfWeek.valueOf(beerAndWurstMenu.getDay().toUpperCase()) == dayOfWeek;
    }

    @Override
    public String getRestaurantName() {
        return "Beer&Wurst";
    }

}
