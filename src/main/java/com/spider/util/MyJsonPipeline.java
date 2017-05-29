package com.spider.util;

import com.alibaba.fastjson.JSON;
import com.spider.entity.GithubRepoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Desc 自定义JsonPipeline
 *
 * @author weijinsheng
 * @date 2017/5/29 10:50
 */
public class MyJsonPipeline implements Pipeline{

    private static final Logger log = LoggerFactory.getLogger(MyJsonPipeline.class);

    public MyJsonPipeline(){

    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        // 解析出数据，do something
       GithubRepoEntity entity = JSON.parseObject(JSON.toJSONString(resultItems.getAll()),GithubRepoEntity.class ) ;
       log.info("result = " + entity.toString());
    }
}
