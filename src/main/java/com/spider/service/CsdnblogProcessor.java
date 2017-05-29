package com.spider.service;

import com.spider.entity.Csdnblog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Desc  xpath 解析
 *
 * @author weijinsheng
 * @date 2017/5/28 14:14
 */
@Service
public class CsdnblogProcessor implements PageProcessor{
    public static String username = "typist_";
    public static int size = 0;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Autowired
    private CsdnblogService csdnblogService;

    @Override
    public void process(Page page) {
        // 列表页
        if (!page.getUrl().regex("http://blog\\.csdn\\.net/" + username + "/article/details/\\d+").match()) {
            // 添加所有文章页
            page.addTargetRequests(page.getHtml().xpath("//div[@id='article_list']").links()// 限定文章列表获取区域
                    .regex("/" + username + "/article/details/\\d+")
                    .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/")// 巧用替换给把相对url转换成绝对url
                    .all());
            // 添加其他列表页
            page.addTargetRequests(page.getHtml().xpath("//div[@id='papelist']").links()// 限定其他列表页获取区域
                    .regex("/" + username + "/article/list/\\d+")
                    .replace("/" + username + "/", "http://blog.csdn.net/" + username + "/")// 巧用替换给把相对url转换成绝对url
                    .all());
            // 文章页
        } else {
            size++;// 文章数量加1
            // 用CsdnBlog类来存抓取到的数据，方便存入数据库
            Csdnblog csdnBlog = new Csdnblog();
            // 设置编号
            csdnBlog.setId(page.getUrl().regex("http://blog\\.csdn\\.net/" + username + "/article/details/(\\d+)").get());
            // 设置标题
            csdnBlog.setTitle(
                    page.getHtml().xpath("//div[@class='article_title']//span[@class='link_title']/a/text()").get());
            // 设置日期
            csdnBlog.setDate(
                    page.getHtml().xpath("//div[@class='article_r']/span[@class='link_postdate']/text()").get());
            // 设置标签（可以有多个，用,来分割）
            csdnBlog.setTags(listToString(page.getHtml()
                    .xpath("//div[@class='article_l']/span[@class='link_categories']/a/allText()").all()));
            // 设置类别（可以有多个，用,来分割）
            csdnBlog.setCategory(
                    listToString(page.getHtml().xpath("//div[@class='category_r']/label/span/text()").all()));
            // 设置阅读人数
            csdnBlog.setView(page.getHtml().xpath("//div[@class='article_r']/span[@class='link_view']").regex("(\\d+)人阅读").get());
            // 设置评论人数
            csdnBlog.setComments(page.getHtml().xpath("//div[@class='article_r']/span[@class='link_comments']").regex("\\((\\d+)\\)").get());
            // 设置是否原创
            csdnBlog.setCopyright(page.getHtml().regex("bog_copyright").match() ? "1" : "0");

            // 把对象存入数据库
            csdnblogService.insert(csdnBlog);

            // 把对象输出控制台
            System.out.println(csdnBlog);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    // 把list转换为string，用,分割
    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }
}
