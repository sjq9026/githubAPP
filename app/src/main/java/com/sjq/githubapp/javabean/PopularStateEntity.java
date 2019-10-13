package com.sjq.githubapp.javabean;

import org.greenrobot.greendao.annotation.Entity;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PopularStateEntity implements Serializable {
    private static final long serialVersionUID = 5312871008L;
   private int position;
   private boolean isFavorite;

    public PopularStateEntity() {

    }



    @Generated(hash = 1721463447)
    public PopularStateEntity(int position, boolean isFavorite) {
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
