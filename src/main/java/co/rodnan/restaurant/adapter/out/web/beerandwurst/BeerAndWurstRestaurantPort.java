package co.rodnan.restaurant.adapter.out.web.beerandwurst;

import co.rodnan.restaurant.application.port.out.RestaurantPort;
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
public class BeerAndWurstRestaurantPort implements RestaurantPort {

    private final BeerAndWurstClient beerAndWurstClient;

    public BeerAndWurstRestaurantPort(@RestClient BeerAndWurstClient beerAndWurstClient) {
        this.beerAndWurstClient = beerAndWurstClient;
    }

    @Override
    public MenuInformation parseMenu() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        BeerAndWurstMenuResponse menuResponse = beerAndWurstClient.getMenu();
        BeerAndWurstMenu wurstMenu = getTodaysMenu(dayOfWeek, menuResponse);
        List<MenuItem> menuItems = getMenuItems(wurstMenu);
        return new MenuInformation(new BigDecimal(menuResponse.getPrice()), menuItems);
    }

    @Override
    public String getRestaurantName() {
        return "Beer&Wurst";
    }

    @Override
    public String getRestaurantId() {
        return "beer_and_wurst";
    }

    private List<MenuItem> getMenuItems(BeerAndWurstMenu wurstMenu) {
        return List.of(
                new MenuItem(wurstMenu.getSoup(), CourseType.SOUP),
                new MenuItem(wurstMenu.getMainCourse(), CourseType.MAIN_COURSE),
                new MenuItem(wurstMenu.getMainCourseB(), CourseType.MAIN_COURSE),
                new MenuItem(wurstMenu.getDessert(), CourseType.DESSERT));
    }

    private BeerAndWurstMenu getTodaysMenu(DayOfWeek dayOfWeek, BeerAndWurstMenuResponse menuResponse) {
        return menuResponse.getMenuItems().stream()
                .filter(beerAndWurstMenu -> isDayToday(dayOfWeek, beerAndWurstMenu))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No menu for given day:" + dayOfWeek));
    }

    private boolean isDayToday(DayOfWeek dayOfWeek, BeerAndWurstMenu beerAndWurstMenu) {
        return DayOfWeek.valueOf(beerAndWurstMenu.getDay().toUpperCase()) == dayOfWeek;
    }

}
