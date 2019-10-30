package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class UserContactTrendingKeyEntity implements Serializable {
    private static final long serialVersionUID = 533989078974232L;
    @Id(autoincrement = true)//设置自增长
    private Long autoId;
    private String user_name;
    private int trending_key_id;
    @Generated(hash = 2021667863)
    public UserContactTrendingKeyEntity(Long autoId, String user_name,
            int trending_key_id) {
        this.autoId = autoId;
        this.user_name = user_name;
        this.trending_key_id = trending_key_id;
    }
    @Generated(hash = 1365445667)
    public UserContactTrendingKeyEntity() {
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
    public int getTrending_key_id() {
        return this.trending_key_id;
    }
    public void setTrending_key_id(int trending_key_id) {
        this.trending_key_id = trending_key_id;
    }

    @Override
    public String toString() {
        return "UserContactTrendingKeyEntity{" +
                "autoId=" + autoId +
                ", user_name='" + user_name + '\'' +
                ", trending_key_id=" + trending_key_id +
                '}';
    }
}
