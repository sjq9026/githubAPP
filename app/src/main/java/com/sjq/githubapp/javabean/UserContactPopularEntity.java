package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class UserContactPopularEntity implements Serializable {
    private static final long serialVersionUID = 5339080808904232L;
    @Id(autoincrement = true)//设置自增长
    private Long autoId;
    private String user_name;
    private int popular_id;
    @Generated(hash = 630434739)
    public UserContactPopularEntity(Long autoId, String user_name, int popular_id) {
        this.autoId = autoId;
        this.user_name = user_name;
        this.popular_id = popular_id;
    }
    @Generated(hash = 1459734442)
    public UserContactPopularEntity() {
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
    public int getPopular_id() {
        return this.popular_id;
    }
    public void setPopular_id(int popular_id) {
        this.popular_id = popular_id;
    }

}
