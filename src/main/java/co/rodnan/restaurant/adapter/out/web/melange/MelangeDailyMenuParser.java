package co.rodnan.restaurant.adapter.out.web.melange;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.enterprise.context.Dependent;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Dependent
public class MelangeDailyMenuParser extends HtmlBasedParser {

    private static final String URL = "http://www.melangekavehaz.hu/menu/heti_menu_ajanlatunk_{period}";

    private static final String COLUMN_SELECTOR = "#oszlop-{index}";

    private static final String DAY_SELECTOR = "#oszlop-{index} > div.nap";

    private static final String SOUP_SELECTOR = "#oszlop-{index} > p:nth-child(2)";

    private static final String MAIN_COURSE_SELECTOR = "#oszlop-{index} > p:nth-child(3)";

    private static final String PRICE_SELECTOR = "#oszlop-{index} > div.ar > span";

    private static final Map<String, DayOfWeek> HUNGARIAN_DAYS = Map.ofEntries(
            Map.entry("Hétfő", DayOfWeek.MONDAY),
            Map.entry("Kedd", DayOfWeek.TUESDAY),
            Map.entry("Szerda", DayOfWeek.WEDNESDAY),
            Map.entry("Csütörtök", DayOfWeek.THURSDAY),
            Map.entry("Péntek", DayOfWeek.FRIDAY)
    );

    List<MelangeDailyMenu> getWeeklyMenuItem() {
        String period = getDatePeriod();
        Document document = getDocument(URL.replace("{period}", period));
        List<MelangeDailyMenu> dailyMenus = new ArrayList<>(5);
        fetchDailyMenus(document, dailyMenus);
        return dailyMenus;
    }

    MelangeDailyMenu getDailyMenu() {
        String period = getDatePeriod();
        Document document = getDocument(URL.replace("{period}", period));
        return getMenuAtGivenDay(document, LocalDate.now().getDayOfWeek().getValue());
    }

    private void fetchDailyMenus(Document document, List<MelangeDailyMenu> dailyMenus) {
        for (int i = 1; i <= 5; i++) {
            dailyMenus.add(getMenuAtGivenDay(document, i));
        }
    }

    private MelangeDailyMenu getMenuAtGivenDay(Document document, int indexOfDay) {
        Elements dayElements = document.select(DAY_SELECTOR.replace("{index}", String.valueOf(indexOfDay)));
        Elements soupSelector = document.select(SOUP_SELECTOR.replace("{index}", String.valueOf(indexOfDay)));
        Elements mainCourseSelector = document.select(MAIN_COURSE_SELECTOR.replace("{index}", String.valueOf(indexOfDay)));
        Elements priceSelector = document.select(PRICE_SELECTOR.replace("{index}", String.valueOf(indexOfDay)));
        return MelangeDailyMenu.builder()
                .day(HUNGARIAN_DAYS.get(dayElements.get(0).childNode(0).toString().trim()))
                .soup(soupSelector.get(0).childNode(0).toString())
                .courseA(getCourseA(mainCourseSelector))
                .courseB(getCourseB(mainCourseSelector))
                .price(new BigDecimal(priceSelector.get(0).childNode(0).toString().replace("Ft", "").replace(".", "").trim()))
                .build();
    }

    private String getMainCourse(Elements mainCourseSelector) {
        return mainCourseSelector.get(0).childNodes().stream()
                .filter(node -> node instanceof TextNode)
                .map(node -> ((TextNode) node).text())
                .collect(Collectors.joining());
    }

    private String getCourseA(Elements mainCourseSelector) {
        try {
            return getMainCourse(mainCourseSelector).split("„B”")[0].replace("„A”", "");
        } catch (IndexOutOfBoundsException e) {
            return "N/A";
        }
    }

    private String getCourseB(Elements mainCourseSelector) {
        try {
            return getMainCourse(mainCourseSelector).split("„B”")[1];
        } catch (IndexOutOfBoundsException e) {
            return "N/A";
        }
    }

    private String getDatePeriod() {
        LocalDate now = LocalDate.now();
        LocalDate monday = now.with(DayOfWeek.MONDAY);
        LocalDate friday = now.with(DayOfWeek.FRIDAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
        return monday.format(formatter) + friday.format(formatter);
    }

}
