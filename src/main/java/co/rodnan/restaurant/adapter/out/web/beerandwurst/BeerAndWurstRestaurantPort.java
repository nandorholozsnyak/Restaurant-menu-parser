package co.rodnan.restaurant.adapter.out.web.beerandwurst;

import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import co.rodnan.restaurant.domain.RestaurantInformation;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BeerAndWurstRestaurantPort implements RestaurantPort {

    private static final String URL = "https://www.beerandwurst.hu";

    private final BeerAndWurstClient beerAndWurstClient;

    public BeerAndWurstRestaurantPort(@RestClient BeerAndWurstClient beerAndWurstClient) {
        this.beerAndWurstClient = beerAndWurstClient;
    }

    @Override
    public MenuInformation getDailyMenu() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        BeerAndWurstMenuResponse menuResponse = beerAndWurstClient.getMenu();
        BeerAndWurstMenu wurstMenu = getTodaysMenu(dayOfWeek, menuResponse);
        List<MenuItem> menuItems = getMenuItems(wurstMenu);
        return new MenuInformation(dayOfWeek, new BigDecimal(menuResponse.getPrice()), menuItems);
    }

    @Override
    public RestaurantInformation getRestaurantInfo() {
        return RestaurantInformation.builder()
                .name("Beer&Wurst")
                .identifier("beer_and_wurst")
                .url(URL)
                .build();
    }

    private List<MenuItem> getMenuItems(BeerAndWurstMenu wurstMenu) {
        return List.of(
                MenuItem.createSoup(wurstMenu.getSoup().trim()),
                MenuItem.createMainCourseA(wurstMenu.getMainCourse().trim()),
                MenuItem.createMainCourseB(wurstMenu.getMainCourseB().trim()),
                MenuItem.createDessert(wurstMenu.getDessert().trim())
        );
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
