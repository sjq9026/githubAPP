package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserContactPopularKeyEntity implements Serializable {
    private static final long serialVersionUID = 53123123134232L;
    @Id(autoincrement = true)//设置自增长
    private Long autoId;
    private String user_name;
    private int popular_key_id;
    @Generated(hash = 558506464)
    public UserContactPopularKeyEntity(Long autoId, String user_name,
            int popular_key_id) {
        this.autoId = autoId;
        this.user_name = user_name;
        this.popular_key_id = popular_key_id;
    }
    @Generated(hash = 30581540)
    public UserContactPopularKeyEntity() {
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
    public int getPopular_key_id() {
        return this.popular_key_id;
    }
    public void setPopular_key_id(int popular_key_id) {
        this.popular_key_id = popular_key_id;
    }

    @Override
    public String toString() {
        return "UserContactPopularKeyEntity{" +
                "autoId=" + autoId +
                ", user_name='" + user_name + '\'' +
                ", popular_key_id=" + popular_key_id +
                '}';
    }
}
