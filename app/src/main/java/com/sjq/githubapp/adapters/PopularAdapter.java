package com.sjq.githubapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sjq.githubapp.R;
import com.sjq.githubapp.javabean.PopularItemEntity;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder>  {
   private ArrayList<PopularItemEntity> list;
    private onPopularItemClickListener listener;
    private Context context;
    public PopularAdapter(Context context,ArrayList<PopularItemEntity> list) {
        this.list = list;
        this.context = context;
    }

    public PopularAdapter(Context context,ArrayList<PopularItemEntity> list,onPopularItemClickListener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item_layout,parent,false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, final int position) {
    final PopularItemEntity item = list.get(position);
        holder.mtitle.setText(list.get(position).getFull_name());
    holder.mInfo.setText(list.get(position).getDescription());
    holder.mStart_tv.setText(list.get(position).getStargazers_count()+"");
    if(item.isFavorite()){
        holder.imageButton.setBackgroundResource(R.drawable.favorite_red);
    }else{
        holder.imageButton.setBackgroundResource(R.drawable.favorite_gray);
    }

        Glide.with(context)

                .load(list.get(position).getOwner().getAvatar_url())
//                .placeholder(R.drawable.defalute_img)

                .into(holder.mAuthor_img);



    holder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onPopularItemClick(position,item);
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

   public interface onPopularItemClickListener{
        void onPopularItemClick(int position,PopularItemEntity popularItemEntity);
        void onFavoriteClick(int position,PopularItemEntity popularItemEntity);
    }
}
