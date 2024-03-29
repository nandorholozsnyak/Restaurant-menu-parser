package co.rodnan.restaurant.adapter.out.web.blahane;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import co.rodnan.restaurant.domain.RestaurantInformation;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class BlahaneRestaurantPort extends HtmlBasedParser implements RestaurantPort {

    private static final String URL = "http://www.menurendeles.hu/";

    private static final String SOUP_SELECTOR = "body > div.main > div.right > div:nth-child(1) > ul > li:nth-child({index})";

    public static final String MAIN_COURSE_SELECTOR = "body > div.main > div.right > div:nth-child(1) > p:nth-child({index})";

    private final BlahaneMenuProperties blahaneMenuProperties;

    @Override
    public MenuInformation getDailyMenu() {
        Document document = getDocument(URL);
        List<MenuItem> menuItems = new ArrayList<>(8);
        menuItems.addAll(collectSoups(document));
        menuItems.addAll(collectMainCourses(document));
        return MenuInformation.builder()
                .price(blahaneMenuProperties.getMenuPrice())
                .menuItems(menuItems)
                .build();
    }

    @Override
    public RestaurantInformation getRestaurantInfo() {
        return RestaurantInformation.builder()
                .name("Blaháné Étterem")
                .identifier("blahane")
                .url(URL)
                .build();
    }

    private List<MenuItem> collectMainCourses(Document document) {
        List<MenuItem> menuItems = new ArrayList<>(5);
        for (int i = 4; i <= 7; i++) {
            Elements soupElement = document.select(MAIN_COURSE_SELECTOR.replace("{index}", String.valueOf(i)));
            menuItems.add(MenuItem.createMainCourse(soupElement.get(0).childNode(0).toString().replace("&nbsp;", "")));
        }
        return menuItems;
    }


    private List<MenuItem> collectSoups(Document document) {
        List<MenuItem> menuItems = new ArrayList<>(3);
        for (int i = 1; i <= 3; i++) {
            Elements soupElement = document.select(SOUP_SELECTOR.replace("{index}", String.valueOf(i)));
            menuItems.add(MenuItem.createSoup(soupElement.get(0).childNode(0).toString()));
        }
        return menuItems;
    }
}
