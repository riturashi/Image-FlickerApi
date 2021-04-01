package com.example.imagemvvmretrofitflickerapi.adapter.viewholder;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;

public class PhotoGridViewHolder extends RecyclerView.ViewHolder {

    public PhotoGridViewHolder(@NonNull View itemView) {
        super(itemView);
        FlexboxLayoutManager.LayoutParams layoutParams = (FlexboxLayoutManager.LayoutParams) itemView.getLayoutParams();
        layoutParams.setFlexGrow(1f);
    }
}
