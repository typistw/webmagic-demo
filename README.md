# webmagic-demo
## 1 简介
基于webmagic实现两个爬虫小列子
## 2 项目相关信息
 maven、spring boot、mybatis、mysql、webmagic、fastjson
## 3 参考
[3.1 web magic](https://github.com/code4craft/webmagic)  
[3.2 基于WebMagic写的一个csdn博客小爬虫](https://github.com/liyifeng1994/webmagic-csdnblog)
## 4 run
4.1 xpath解析: 直接运行 **SpiderDemoApplication** 类中main函数【参考于3.1】  
4.2 json解析：自定义jsonPileline  
```
@Override
public void process(ResultItems resultItems, Task task) {
	// 解析出数据，do something
   GithubRepoEntity entity = JSON.parseObject(JSON.toJSONString(resultItems.getAll()),GithubRepoEntity.class ) ;
   log.info("result = " + entity.toString());
}
```  
直接运行 **GithubRepoPageProcessor** 类中main函数【参考于3.2】


