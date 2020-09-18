package co.rodnan.restaurant.adapter.out.web.flaska;

import co.rodnan.restaurant.adapter.out.web.common.HtmlBasedParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import javax.enterprise.context.Dependent;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Dependent
public class FlaskaMenuParser extends HtmlBasedParser {

    private static final String DAILY_MENU_URL = "http://flaska.hu/index.php/heti-menu/";
    private static final String MENU_SELECTOR = "#post-83 > div > div";

    FlaskaDailyMenu getDailyMenuAtDay(DayOfWeek dayOfWeek) {
        return getDailyMenus().stream()
                .filter(flaskaDailyMenu -> flaskaDailyMenu.getDay() == dayOfWeek)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Menu at day:[" + dayOfWeek + "] is not available"));
    }

    List<FlaskaDailyMenu> getDailyMenus() {
        List<FlaskaDailyMenu> resultList = new ArrayList<>(4);
        List<String> rawMenuLines = this.getRawMenuLines();
        for (int i = 1; i <= 4; i++) {
            resultList.add(getFlaskaDailyMenu(rawMenuLines, i));
        }
        return resultList;
    }

    private FlaskaDailyMenu getFlaskaDailyMenu(List<String> rawMenuLines, int index) {
        String title = getTitle(rawMenuLines, index);
        String[] cleanedTitleParts = getCleanedTitle(title);
        String rawDay = cleanedTitleParts[0];
        String rawPrice = getRawPrice(cleanedTitleParts[1]);
        return FlaskaDailyMenu.builder()
                .day(HUNGARIAN_DAYS.get(rawDay))
                .price(new BigDecimal(rawPrice))
                .soup(getSoup(rawMenuLines, index))
                .mainCourse(getMainCourse(rawMenuLines, index))
                .build();
    }

    private String getMainCourse(List<String> rawMenuLines, int index) {
        return rawMenuLines.get(index * 3 + 2);
    }

    private String getSoup(List<String> rawMenuLines, int index) {
        return rawMenuLines.get(index * 3 + 1);
    }

    private String getTitle(List<String> rawMenuLines, int index) {
        return rawMenuLines.get(index * 3).trim();
    }

    private String getRawPrice(String cleanedTitlePart) {
        return cleanedTitlePart.replace(".-", "").trim();
    }

    private String[] getCleanedTitle(String title) {
        return Stream.of(title.split(" ")).filter(s -> !"".equals(s)).toArray(String[]::new);
    }

    private List<String> getRawMenuLines() {
        Document document = getDocument(DAILY_MENU_URL);
        Elements select = document.select(MENU_SELECTOR);
        return select.get(0).childNodes().stream()
                .flatMap(node -> node.childNodes().stream())
                .map(this::valueOf)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
    }

    String valueOf(Node node) {
        if (node instanceof TextNode) {
            return node.toString().replaceAll("&nbsp", "").replace(";", "").trim();
        }
        return valueOf(node.childNode(0));
    }

}
