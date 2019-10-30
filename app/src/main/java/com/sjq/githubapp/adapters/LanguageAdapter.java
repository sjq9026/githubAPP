package com.sjq.githubapp.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sjq.githubapp.R;
import com.sjq.githubapp.javabean.PopularKeyEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LanguageAdapter extends BaseQuickAdapter<PopularKeyEntity, BaseViewHolder> {
    private boolean isEdit;

    public LanguageAdapter( @Nullable List<PopularKeyEntity> data) {
        super(R.layout.language_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PopularKeyEntity item) {
        helper.setText(R.id.language_name_tv,item.getName())
                .setChecked(R.id.check_box,item.getChecked())
                .addOnClickListener(R.id.check_box);

            helper.setVisible(R.id.check_box,isEdit);

    }

   public void  setEditAble(boolean isEidt){
        this.isEdit = isEidt;
        notifyDataSetChanged();
   }
}
