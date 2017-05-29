package com.spider;

import com.alibaba.druid.pool.DruidDataSource;
import com.spider.service.CsdnblogProcessor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import us.codecraft.webmagic.Spider;

import javax.sql.DataSource;

@SpringBootApplication
public class SpiderDemoApplication {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource(){
		return  new DruidDataSource();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/**/*.xml"));
			sqlSessionFactory = sqlSessionFactoryBean.getObject();
		} catch (Exception e){
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}

	private CsdnblogProcessor csdnblogProcessor;

	@Autowired
	public void autoCsdnBlobProcessor(CsdnblogProcessor csdnblogProcessor){
		this.csdnblogProcessor = csdnblogProcessor;
		long startTime, endTime;
		System.out.println("耐心等待，一波数据正在路上!");
		startTime = System.currentTimeMillis();
		Spider.create(this.csdnblogProcessor).addUrl("http://blog.csdn.net/" + CsdnblogProcessor.username).thread(5).run();
		endTime = System.currentTimeMillis();
		System.out.println("【爬虫结束】共抓取" + CsdnblogProcessor.size + "篇文章，耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpiderDemoApplication.class, args);
	}
}
