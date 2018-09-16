package com.world.ico.spider;


import com.world.ico.dto.ListUrlUnit;
import com.world.ico.dto.UrlMsg;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;


/**
 * Created by youmingwei on 16/4/28.
 */
public class ListPageSpider {
    public static final Logger logger = LoggerFactory.getLogger(ListPageSpider.class);



    public int crawlerUrls(UrlMsg urlMsg) {
        urlMsg.setType(2);
        urlMsg.setClientHost("127.0.0.1");
        urlMsg.setClientPID("10178");
        ListUrlUnit listUrlUnit = urlMsg.getListUrlUnit();

        Document document;
        try {
            document = Jsoup.connect(listUrlUnit.getListUrl())
                    .userAgent("Mozilla")
                    .timeout(10000).get();
            String rule = listUrlUnit.getListRule();
            String[] rules = rule.split("@");
            Elements elements = new Elements();
            for (String string: rules) {
                Elements elements1 = document.select(string);
                elements.addAll(elements1);
            }
            HashSet<String> stringHashSet = new HashSet<>();
            int res=recurGetUrlFromElements(listUrlUnit.getRootUrl(), elements, stringHashSet);
            listUrlUnit.setStringHashSet(stringHashSet);
            urlMsg.setSolveResultType(1);
            return res;
        } catch (Exception e) {
            logger.info("spider exception:" + e.getMessage());
            urlMsg.setSolveResultType(0);
            return 0;
        }

    }
    /**
     * 递归获取url
     * @param rootUrl
     * @param elements
     * @param hashSet
     */
    public int recurGetUrlFromElements(String rootUrl, Elements elements, HashSet<String> hashSet) {
        if (elements.size() == 0) {
            return 0;
        }
        for (Element element: elements) {
            for (Element element1 : element.getElementsByAttribute("href")) {

                String formatUrl;
                String relUrl = element1.attr("href");
                if (relUrl.startsWith(".")) {
                    relUrl = relUrl.substring(1);
                } else {
                }
                if (rootUrl.equals("null")) {
                    formatUrl = relUrl;
                } else {
                    formatUrl = rootUrl + relUrl;
                }
                logger.info("format url:" + formatUrl);
                hashSet.add(formatUrl);
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        UrlMsg urlMsg = new UrlMsg();
        ListUrlUnit listUrlUnit = new ListUrlUnit();
        listUrlUnit.setListUrl("http://www.efsa.europa.eu/en/news");
        listUrlUnit.setListRule("div[class=block__content]");
        listUrlUnit.setRootUrl("http://www.efsa.europa.eu");
        urlMsg.setListUrlUnit(listUrlUnit);
        new ListPageSpider().crawlerUrls(urlMsg);
    }
}
