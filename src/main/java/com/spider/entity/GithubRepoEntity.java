package com.spider.entity;

/**
 * Desc GithubRepoPageProcesor 抓取结果的JSON结构
 *
 * @author weijinsheng
 * @date 2017/5/29 10:55
 */
public class GithubRepoEntity {
    private String author;
    private String name;
    private Readme readme;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Readme getReadme() {
        return readme;
    }

    public void setReadme(Readme readme) {
        this.readme = readme;
    }

    @Override
    public String toString() {
        return "GithubRepoEntity{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", readme.FirstSourceText=" + readme.getFirstSourceText() +
                '}';
    }

    public static class Readme{
        private  String firstSourceText;

        public String getFirstSourceText() {
            return firstSourceText;
        }

        public void setFirstSourceText(String firstSourceText) {
            this.firstSourceText = firstSourceText;
        }
    }
}
