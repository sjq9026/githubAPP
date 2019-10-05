package com.sjq.githubapp.db.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DemoEntity {

    @Id(autoincrement = true)//设置自增长
    private Long autoId;

    @Index(unique = true)//设置唯一性
    private String id;//人员编号

    private String name;//人员姓名

    @Generated(hash = 1337287682)
    public DemoEntity(Long autoId, String id, String name) {
        this.autoId = autoId;
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1979632871)
    public DemoEntity() {
    }

    public Long getAutoId() {
        return this.autoId;
    }

    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
