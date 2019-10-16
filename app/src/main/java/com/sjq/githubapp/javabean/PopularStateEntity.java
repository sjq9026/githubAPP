package com.sjq.githubapp.javabean;



import java.io.Serializable;



public class PopularStateEntity implements Serializable {
    private static final long serialVersionUID = 5312871008L;
   private int position;
   private boolean isFavorite;
   private int popular_id;

    public PopularStateEntity() {

    }


    public int getPopular_id() {
        return popular_id;
    }

    public void setPopular_id(int popular_id) {
        this.popular_id = popular_id;
    }

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
