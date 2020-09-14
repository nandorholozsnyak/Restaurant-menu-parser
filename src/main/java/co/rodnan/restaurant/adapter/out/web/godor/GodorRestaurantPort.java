package co.rodnan.restaurant.adapter.out.web.godor;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static co.rodnan.restaurant.domain.CourseType.UNKNOWN;

@ApplicationScoped
public class GodorRestaurantPort extends HtmlBasedParser implements RestaurantPort {

    private static final String URL = "http://www.godor.hu/";

    private static final String MENU_ELEMENT_CSS_SELECTOR = "body > div.header-wrapper > div.container > div > div.content > div.daily-text > div > div:nth-child({index}) > span.name";

    @Override
    public MenuInformation parseMenu() {
        Document document = getDocument(URL);
        Elements menuElements = document.select("body > div.header-wrapper > div.container > div > div.content > div.daily-text > div");
        List<MenuItem> menuItems = getMenuItems(document, menuElements);
        return MenuInformation.builder()
                .price(BigDecimal.ZERO)
                .menuItems(menuItems)
                .build();
    }

    @Override
    public String getRestaurantName() {
        return "Gödör";
    }

    @Override
    public String getRestaurantId() {
        return "godor";
    }

    private List<MenuItem> getMenuItems(Document document, Elements menuElements) {
        int menuItemCount = getMenuItemCount(menuElements);
        List<MenuItem> menuItems = new ArrayList<>(menuItemCount);
        for (int i = 1; i <= menuItemCount; i++) {
            Elements menuItem = document.select(MENU_ELEMENT_CSS_SELECTOR.replace("{index}", String.valueOf(i)));
            String courseName = menuItem.get(0).childNode(0).toString();
            menuItems.add(MenuItem.builder()
                    .name(courseName)
                    .type(UNKNOWN)
                    .build());
        }
        return menuItems;
    }

    private int getMenuItemCount(Elements menuElements) {
        return (int) menuElements.get(0)
                .childNodes()
                .stream()
                .filter(element -> !(element instanceof TextNode))
                .count();
    }
}
