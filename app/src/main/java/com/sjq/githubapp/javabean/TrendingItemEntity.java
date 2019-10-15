package com.sjq.githubapp.javabean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class TrendingItemEntity implements Serializable {

    private static final long serialVersionUID = 53687111112L;


    /**
     * repo : Snailclimb/JavaGuide
     * repo_link : https://github.com/Snailclimb/JavaGuide
     * desc : 【Java学习+面试指南】 一份涵盖大部分Java程序员所需要掌握的核心知识。
     * lang : Java
     * stars : 57,631
     * forks : 19,297
     * added_stars : 1,195 stars this week
     * avatars : ["https://avatars0.githubusercontent.com/u/29880145?s=40&v=4","https://avatars0.githubusercontent.com/u/43314997?s=40&v=4","https://avatars3.githubusercontent.com/u/5917359?s=40&v=4","https://avatars3.githubusercontent.com/u/3983683?s=40&v=4","https://avatars3.githubusercontent.com/u/27286340?s=40&v=4"]
     */

    private String repo;
    private String repo_link;
    private String desc;
    private String lang;
    private String stars;
    private String forks;
    private String added_stars;
    private ArrayList<String> avatars;
    private boolean isFavorite;

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getRepo_link() {
        return repo_link;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setRepo_link(String repo_link) {
        this.repo_link = repo_link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getAdded_stars() {
        return added_stars;
    }

    public void setAdded_stars(String added_stars) {
        this.added_stars = added_stars;
    }

    public ArrayList<String> getAvatars() {
        return avatars;
    }

    public void setAvatars(ArrayList<String> avatars) {
        this.avatars = avatars;
    }

    @Override
    public String toString() {
        return "TrendingItemEntity{" +
                "repo='" + repo + '\'' +
                ", repo_link='" + repo_link + '\'' +
                ", desc='" + desc + '\'' +
                ", lang='" + lang + '\'' +
                ", stars='" + stars + '\'' +
                ", forks='" + forks + '\'' +
                ", added_stars='" + added_stars + '\'' +
                ", avatars=" + avatars +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
