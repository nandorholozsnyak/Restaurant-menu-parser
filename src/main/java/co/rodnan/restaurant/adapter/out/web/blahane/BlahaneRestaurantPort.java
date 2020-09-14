package co.rodnan.restaurant.adapter.out.web.blahane;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.CourseType;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BlahaneRestaurantPort extends HtmlBasedParser implements RestaurantPort {

    private static final String URL = "http://www.menurendeles.hu/";

    private static final String SOUP_SELECTOR = "body > div.main > div.right > div:nth-child(1) > ul > li:nth-child({index})";

    public static final String MAIN_COURSE_SELECTOR = "body > div.main > div.right > div:nth-child(1) > p:nth-child({index})";

    @Override
    public MenuInformation parseMenu() {
        Document document = getDocument(URL);
        List<MenuItem> menuItems = new ArrayList<>(8);
        menuItems.addAll(collectSoups(document));
        menuItems.addAll(collectMainCourses(document));
        return MenuInformation.builder()
                .price(BigDecimal.ZERO)
                .menuItems(menuItems)
                .build();
    }

    private List<MenuItem> collectMainCourses(Document document) {
        List<MenuItem> menuItems = new ArrayList<>(5);
        for (int i = 4; i <= 7; i++) {
            Elements soupElement = document.select(MAIN_COURSE_SELECTOR.replace("{index}", String.valueOf(i)));
            menuItems.add(MenuItem.builder()
                    .name(soupElement.get(0).childNode(0).toString())
                    .type(CourseType.MAIN_COURSE)
                    .build());
        }
        return menuItems;
    }


    private List<MenuItem> collectSoups(Document document) {
        List<MenuItem> menuItems = new ArrayList<>(3);
        for (int i = 1; i <= 3; i++) {
            Elements soupElement = document.select(SOUP_SELECTOR.replace("{index}", String.valueOf(i)));
            menuItems.add(MenuItem.builder()
                    .name(soupElement.get(0).childNode(0).toString())
                    .type(CourseType.SOUP)
                    .build());
        }
        return menuItems;
    }

    @Override
    public String getRestaurantName() {
        return "Blaháné Étterem";
    }

    @Override
    public String getRestaurantId() {
        return "blahane";
    }
}
