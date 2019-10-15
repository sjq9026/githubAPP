package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class PopularFavoriteEntity implements Serializable {
    private static final long serialVersionUID = 5268710012L;
    @Id(autoincrement = true)//设置自增长
    private Long autoId;
    private int popularId;
    private String full_name;//标题
   private String  description;//描述
   private String avatar_url;//头像地址
   private int stargazers_count;//start数
    private String html_url;//网址
    @Generated(hash = 961595420)
    public PopularFavoriteEntity(Long autoId, int popularId, String full_name,
            String description, String avatar_url, int stargazers_count,
            String html_url) {
        this.autoId = autoId;
        this.popularId = popularId;
        this.full_name = full_name;
        this.description = description;
        this.avatar_url = avatar_url;
        this.stargazers_count = stargazers_count;
        this.html_url = html_url;
    }
    @Generated(hash = 2005225296)
    public PopularFavoriteEntity() {
    }
    public Long getAutoId() {
        return this.autoId;
    }
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }
    public int getPopularId() {
        return this.popularId;
    }
    public void setPopularId(int popularId) {
        this.popularId = popularId;
    }
    public String getFull_name() {
        return this.full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAvatar_url() {
        return this.avatar_url;
    }
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
    public int getStargazers_count() {
        return this.stargazers_count;
    }
    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }
    public String getHtml_url() {
        return this.html_url;
    }
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
