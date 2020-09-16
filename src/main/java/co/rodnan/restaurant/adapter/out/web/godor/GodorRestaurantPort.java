package co.rodnan.restaurant.adapter.out.web.godor;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import co.rodnan.restaurant.domain.RestaurantInformation;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GodorRestaurantPort extends HtmlBasedParser implements RestaurantPort {

    private static final String URL = "http://www.godor.hu/";

    private static final String MENU_ELEMENT_CSS_SELECTOR = "body > div.header-wrapper > div.container > div > div.content > div.daily-text > div > div:nth-child({index}) > span.name";

    @Override
    public MenuInformation getDailyMenu() {
        Document document = getDocument(URL);
        Elements menuElements = document.select("body > div.header-wrapper > div.container > div > div.content > div.daily-text > div");
        List<MenuItem> menuItems = getMenuItems(document, menuElements);
        return MenuInformation.builder()
                .price(BigDecimal.ZERO)
                .menuItems(menuItems)
                .build();
    }

    @Override
    public RestaurantInformation getRestaurantInfo() {
        return RestaurantInformation.builder()
                .name("Gödör")
                .identifier("godor")
                .url(URL)
                .build();
    }

    private List<MenuItem> getMenuItems(Document document, Elements menuElements) {
        int menuItemCount = getMenuItemCount(menuElements);
        List<MenuItem> menuItems = new ArrayList<>(menuItemCount);
        for (int elementIndex = 1; elementIndex <= menuItemCount; elementIndex++) {
            Elements menuItem = document.select(MENU_ELEMENT_CSS_SELECTOR.replace("{index}", String.valueOf(elementIndex)));
            String courseName = menuItem.get(0).childNode(0).toString();
            menuItems.add(getSpecificMenuItem(elementIndex, courseName));
        }
        return menuItems;
    }

    private MenuItem getSpecificMenuItem(int elementIndex, String courseName) {
        return isSoupIndex(elementIndex)
                ? MenuItem.createSoup(courseName)
                : MenuItem.createMainCourse(courseName);
    }

    private int getMenuItemCount(Elements menuElements) {
        return (int) menuElements.get(0)
                .childNodes()
                .stream()
                .filter(element -> !(element instanceof TextNode))
                .count();
    }

    private boolean isSoupIndex(int index) {
        return index <= 3;
    }
}
