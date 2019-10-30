package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class UserContactTrendingEntity implements Serializable {
    private static final long serialVersionUID = 53234234123123232L;
    @Id(autoincrement = true)//设置自增长
    private Long autoId;
    private String user_name;
    private int trending_repo;
    @Generated(hash = 209277823)
    public UserContactTrendingEntity(Long autoId, String user_name,
            int trending_repo) {
        this.autoId = autoId;
        this.user_name = user_name;
        this.trending_repo = trending_repo;
    }
    @Generated(hash = 1269410865)
    public UserContactTrendingEntity() {
    }
    public Long getAutoId() {
        return this.autoId;
    }
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }
    public String getUser_name() {
        return this.user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public int getTrending_repo() {
        return this.trending_repo;
    }
    public void setTrending_repo(int trending_repo) {
        this.trending_repo = trending_repo;
    }

}
