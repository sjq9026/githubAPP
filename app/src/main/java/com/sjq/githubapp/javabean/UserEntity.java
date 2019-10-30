package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserEntity {
    @Id(autoincrement = true)//设置自增长
    private Long autoId;

    private String user_name;
    private String token;
    @Generated(hash = 691913765)
    public UserEntity(Long autoId, String user_name, String token) {
        this.autoId = autoId;
        this.user_name = user_name;
        this.token = token;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
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
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "autoId=" + autoId +
                ", user_name='" + user_name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
