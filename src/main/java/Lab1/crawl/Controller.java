package Lab1.crawl;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import static Lab1.crawl.Constants.WIKI_LINK;

public class Controller {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "src/main/resources/";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed(WIKI_LINK);

        CrawlController.WebCrawlerFactory<WebCrawler> factory = WikiCrawler::new;

        controller.start(factory, numberOfCrawlers);
        controller.shutdown();
        controller.getPageFetcher().shutDown();
    }
}
