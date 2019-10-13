package com.sjq.githubapp.views;

import com.sjq.githubapp.base.BaseView;

public interface PopularDetailView extends BaseView {

  void  onItemFavoriteStatusChange(int position,boolean isFavorite);


}
