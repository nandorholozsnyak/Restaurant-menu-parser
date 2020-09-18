package co.rodnan.restaurant.adapter.out.web.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Map;

/**
 *
 */
public class HtmlBasedParser {

    protected static final Map<String, DayOfWeek> HUNGARIAN_DAYS = Map.ofEntries(
            Map.entry("Hétfő", DayOfWeek.MONDAY),
            Map.entry("Kedd", DayOfWeek.TUESDAY),
            Map.entry("Szerda", DayOfWeek.WEDNESDAY),
            Map.entry("Csütörtök", DayOfWeek.THURSDAY),
            Map.entry("Péntek", DayOfWeek.FRIDAY)
    );

    /**
     * @param url
     * @return
     */
    protected Document getDocument(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new HtmlDocumentParseException("Error during connecting to url:" + url, e);
        }
        return doc;
    }
}
