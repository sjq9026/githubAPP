package com.sjq.githubapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sjq.githubapp.R;
import com.sjq.githubapp.javabean.PopularItemEntity;
import com.sjq.githubapp.javabean.TrendingItemEntity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.PopularViewHolder>  {
   private ArrayList<TrendingItemEntity> list;
    private onTrendingItemClickListener listener;
    private Context context;
    public TrendingAdapter(Context context, ArrayList<TrendingItemEntity> list) {
        this.list = list;
        this.context = context;
    }

    public TrendingAdapter(Context context, ArrayList<TrendingItemEntity> list, onTrendingItemClickListener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    public void dataChange(ArrayList<TrendingItemEntity> list){
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item_layout,parent,false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, final int position) {
    final TrendingItemEntity item = list.get(position);
        holder.mtitle.setText(item.getRepo());
    holder.mInfo.setText(item.getDesc());
    holder.mStart_tv.setText(item.getStars());
    if(item.isFavorite()){
        holder.imageButton.setBackgroundResource(R.drawable.favorite_red);
    }else{
        holder.imageButton.setBackgroundResource(R.drawable.favorite_gray);
    }
    if(item.getAvatars()!=null &&  item.getAvatars().size() > 0){
        Glide.with(context)
                .load(list.get(position).getAvatars().get(0))
                .into(holder.mAuthor_img);
    }





    holder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onTrendingItemClick(position,item);
        }
    }
    });

    holder.imageButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(listener != null){
                listener.onFavoriteClick(position,item);
            }
        }
    });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder{
        private TextView mtitle;
        private TextView mInfo;
        private RoundedImageView mAuthor_img;
        private TextView mStart_tv;
        private ImageView imageButton;
        private CardView cardView;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.wrap);
            mtitle = itemView.findViewById(R.id.popular_title_tv) ;
            mInfo = itemView.findViewById(R.id.info_tv);
            mAuthor_img = itemView.findViewById(R.id.author_img);
            mStart_tv = itemView.findViewById(R.id.start_tv);
            imageButton = itemView.findViewById(R.id.is_favorite_img_btn);
        }
    }

   public interface onTrendingItemClickListener{
        void onTrendingItemClick(int position, TrendingItemEntity trendingItemEntity);
        void onFavoriteClick(int position, TrendingItemEntity trendingItemEntity);
    }
}
