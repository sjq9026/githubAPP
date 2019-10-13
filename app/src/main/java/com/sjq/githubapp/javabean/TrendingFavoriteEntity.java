package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class TrendingFavoriteEntity implements Serializable {
    private static final long serialVersionUID = 5268711012L;
    @Id(autoincrement = true)//设置自增长
    private Long autoId;
    private int trendingId;
    @Generated(hash = 890009879)
    public TrendingFavoriteEntity(Long autoId, int trendingId) {
        this.autoId = autoId;
        this.trendingId = trendingId;
    }
    @Generated(hash = 851652243)
    public TrendingFavoriteEntity() {
    }
    public Long getAutoId() {
        return this.autoId;
    }
    public void setAutoId(Long autoId) {
        this.autoId = autoId;
    }
    public int getTrendingId() {
        return this.trendingId;
    }
    public void setTrendingId(int trendingId) {
        this.trendingId = trendingId;
    }
}
