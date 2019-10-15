package com.sjq.githubapp.javabean;

import java.io.Serializable;
import java.util.ArrayList;

public class TrendingResponse implements Serializable {
//https://trendings.herokuapp.com/repo?lang=java&since=weekly

    /**
     * count : 25
     * msg : suc
     */

    private int count;
    private String msg;
    private ArrayList<TrendingItemEntity> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<TrendingItemEntity> getTrendingItemEntities() {
        return items;
    }

    public void setTrendingItemEntities(ArrayList<TrendingItemEntity> trendingItemEntities) {
        this.items = trendingItemEntities;
    }
}
