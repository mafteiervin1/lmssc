package Lab1.crawl;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

import static Lab1.crawl.Constants.WIKI_LINK;

public class WikiCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
        + "|png|mp3|mp4|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
            && href.startsWith(WIKI_LINK);
    }

    @Override
    public void visit(Page page) {
        if (page.getParseData() instanceof HtmlParseData) {

            try {
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                String text = htmlParseData.getText();
                String title = htmlParseData.getTitle();
                String html = htmlParseData.getHtml();
                Set <WebURL> links = htmlParseData.getOutgoingUrls();
                System.out.println("Title: " + title);
                System.out.println("Text length: " + text.length());
                System.out.println("Html length: " + html.length());
                System.out.println("Number of outgoing links: " + links.size());
                System.out.println("=============");

                TextProcessor.writeToFile(text);

            } catch (IOException e1) {
                System.out.println("Error during reading/writing");
            }
        }
    }
//
//    private void appendToFile(String text) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
//        writer.append(' ');
//        writer.append(text);
//
//        writer.close();
//    }
}
