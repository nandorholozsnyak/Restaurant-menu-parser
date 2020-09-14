package co.rodnan.restaurant.adapter.out.web.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 *
 */
public class HtmlBasedParser {

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
