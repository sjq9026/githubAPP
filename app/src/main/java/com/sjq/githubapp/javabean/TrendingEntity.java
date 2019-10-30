package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class TrendingEntity implements Serializable {



    private static final long serialVersionUID = 526872312311012L;
    @Id(autoincrement = true)//设置自增长
    private Long autoId;
    private String repo;
    private String repo_link;
    private String desc;
    private String lang;
    private String stars;
    private String forks;
    private String added_stars;
    private String avatarImg;
    @Generated(hash = 1976291164)
    public TrendingEntity(Long autoId, String repo, String repo_link, String desc,
            String lang, String stars, String forks, String added_stars,
            String avatarImg) {
        this.autoId = autoId;
        this.repo = repo;
        this.repo_link = repo_link;
        this.desc = desc;
        this.lang = lang;
        this.stars = stars;
        this.forks = forks;
        this.added_stars = added_stars;
        this.avatarImg = avatarImg;
    }
    @Generated(hash = 450917417)
    public TrendingEntity() {
    }
    public Long getAutoId() {
        return this.autoId;
    }
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }
    public String getRepo() {
        return this.repo;
    }
    public void setRepo(String repo) {
        this.repo = repo;
    }
    public String getRepo_link() {
        return this.repo_link;
    }
    public void setRepo_link(String repo_link) {
        this.repo_link = repo_link;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getLang() {
        return this.lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public String getStars() {
        return this.stars;
    }
    public void setStars(String stars) {
        this.stars = stars;
    }
    public String getForks() {
        return this.forks;
    }
    public void setForks(String forks) {
        this.forks = forks;
    }
    public String getAdded_stars() {
        return this.added_stars;
    }
    public void setAdded_stars(String added_stars) {
        this.added_stars = added_stars;
    }
    public String getAvatarImg() {
        return this.avatarImg;
    }
    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }



}
