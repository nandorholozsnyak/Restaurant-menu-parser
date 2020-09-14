package co.rodnan.restaurant.adapter.out.web.melange;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import co.rodnan.restaurant.application.port.out.RestaurantPort;
import co.rodnan.restaurant.domain.CourseType;
import co.rodnan.restaurant.domain.MenuInformation;
import co.rodnan.restaurant.domain.MenuItem;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class MelangeRestaurantPort extends HtmlBasedParser implements RestaurantPort {

    private static final String URL = "http://www.melangekavehaz.hu";

    @Override
    public MenuInformation parseMenu() {
        return new MenuInformation(BigDecimal.valueOf(1390), getMenu());
    }

    @Override
    public String getRestaurantName() {
        return "Melange Kávéház";
    }

    @Override
    public String getRestaurantId() {
        return "melange";
    }

    private List<MenuItem> getMenu() {
        Document doc = getDocument(URL);
        MenuItem soup = getSoup(doc);
        MenuItem firstMainCourse = getFirstMainCourse(doc);
        MenuItem secondMainCourse = getSecondMainCourse(doc);
        return List.of(soup, firstMainCourse, secondMainCourse);
    }

    private MenuItem getSecondMainCourse(Document doc) {
        Elements bMainCourseFirstPart = doc.select("body > div.Feher > div > table > tbody > tr:nth-child(2) > td.menu > p:nth-child(8) > span:nth-child(2)");
        Elements bMainCourseSecondPart = doc.select("body > div.Feher > div > table > tbody > tr:nth-child(2) > td.menu > p:nth-child(9) > span");
        return new MenuItem(bMainCourseFirstPart.text() + " " + bMainCourseSecondPart.text(), CourseType.MAIN_COURSE);
    }

    private MenuItem getFirstMainCourse(Document doc) {
        Elements aMainCourseFirstPart = doc.select("body > div.Feher > div > table > tbody > tr:nth-child(2) > td.menu > p:nth-child(6) > span:nth-child(2)");
        Elements aMainCourseSecondPart = doc.select("body > div.Feher > div > table > tbody > tr:nth-child(2) > td.menu > p:nth-child(7) > span");
        return new MenuItem(aMainCourseFirstPart.text() + " " + aMainCourseSecondPart.text(), CourseType.MAIN_COURSE);
    }

    private MenuItem getSoup(Document doc) {
        Elements soupFirstPart = doc.select("body > div.Feher > div > table > tbody > tr:nth-child(2) > td.menu > p:nth-child(3) > span");
        Elements soupSecondPart = doc.select("body > div.Feher > div > table > tbody > tr:nth-child(2) > td.menu > p:nth-child(4) > span");
        Elements soupThirdPart = doc.select("body > div.Feher > div > table > tbody > tr:nth-child(2) > td.menu > p:nth-child(5) > span");
        return new MenuItem(soupFirstPart.text() + " " + soupSecondPart.text() + " " + soupThirdPart.text(), CourseType.SOUP);
    }

}
