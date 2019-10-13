package com.sjq.githubapp.javabean;

import java.io.Serializable;
import java.util.ArrayList;

public class PopularResponse implements Serializable {

    /**
     * total_count : 4776
     * incomplete_results : false
     */

    private int total_count;
    private boolean incomplete_results;
    private ArrayList<PopularItemEntity> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<PopularItemEntity> getItemEntities() {
        return items;
    }

    public void setItemEntities(ArrayList<PopularItemEntity> itemEntities) {
        this.items = itemEntities;
    }

    @Override
    public String toString() {
        return "PopularResponse{" +
                "total_count=" + total_count +
                ", incomplete_results=" + incomplete_results +
                ", itemEntities=" + items +
                '}';
    }
}
