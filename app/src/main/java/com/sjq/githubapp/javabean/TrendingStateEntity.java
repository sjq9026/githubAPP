package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;


public class TrendingStateEntity implements Serializable {
    private static final long serialVersionUID = 51112871008L;
   private int position;
   private boolean isFavorite;

    public TrendingStateEntity() {

    }




    public TrendingStateEntity(int position, boolean isFavorite) {
        this.position = position;
        this.isFavorite = isFavorite;
    }



    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }



    public boolean getIsFavorite() {
        return this.isFavorite;
    }



    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
