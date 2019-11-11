package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class TrendingKeyEntity implements Serializable {
    private static final long serialVersionUID = 53687103234232L;

    /**
     * path : crystal
     * name : Crystal
     * checked : false
     */
    @Id(autoincrement = true)
    //设置自增长
    private Long autoId;
    private int id;
    private String path;
    private String name;
    private boolean checked;

    @Generated(hash = 1368573186)
    public TrendingKeyEntity(Long autoId, int id, String path, String name,
            boolean checked) {
        this.autoId = autoId;
        this.id = id;
        this.path = path;
        this.name = name;
        this.checked = checked;
    }

    @Generated(hash = 1087700166)
    public TrendingKeyEntity() {
    }

    public Long getAutoId() {
        return this.autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
