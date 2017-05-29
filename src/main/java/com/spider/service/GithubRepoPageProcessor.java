package com.spider.service;

import com.spider.util.MyJsonPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Desc implements PageProcessor way
 *
 * @author weijinsheng
 * @date 2017/5/26 9:41
 */
public class GithubRepoPageProcessor implements PageProcessor{

    private static List<String> links;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        links = page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all();
        page.addTargetRequests(links);
        page.putField("author",page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//h1[@class='public']/strong/a/text()").toString());
        if(page.getResultItems().get("name") == null){
            // skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
       Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft")
//                .addPipeline(new JsonFilePipeline("G:\\data\\webmagic")).thread(5).run();
                .addPipeline(new MyJsonPipeline()).thread(5).run();
    }
}
