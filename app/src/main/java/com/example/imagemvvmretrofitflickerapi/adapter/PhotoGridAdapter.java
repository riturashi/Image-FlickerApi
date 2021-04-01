package com.example.imagemvvmretrofitflickerapi.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.imagemvvmretrofitflickerapi.R;
import com.example.imagemvvmretrofitflickerapi.adapter.viewholder.PhotoGridViewHolder;
import com.example.imagemvvmretrofitflickerapi.model.PhotoListModel;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;



public class PhotoGridAdapter extends RecyclerView.Adapter<PhotoGridViewHolder> {

    private List<PhotoListModel> mPhotos;

    public PhotoGridAdapter(List<PhotoListModel> photos) {
        mPhotos = photos;
    }

    public void setDataSource(List<PhotoListModel> photos) {
        if (mPhotos != null) {
            mPhotos.clear();
        }
        mPhotos = photos;
    }

    public void addPhotos(List<PhotoListModel> photos) {
        if (mPhotos != null) {
            mPhotos.addAll(photos);
        }
    }

    @NonNull
    @Override
    public PhotoGridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PhotoGridViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_photo_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoGridViewHolder photoGridViewHolder, int i) {
        ImageView imageView = (ImageView) photoGridViewHolder.itemView;
        FlexboxLayoutManager.LayoutParams layoutParams = (FlexboxLayoutManager.LayoutParams) imageView.getLayoutParams();

        if (i % 2 == 0) {
            layoutParams.setWidth(250);
        } else {
            layoutParams.setWidth(400);
        }
        Glide.with(photoGridViewHolder.itemView.getContext())
                .load(mPhotos.get(i).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mPhotos != null ? mPhotos.size() : 0;
    }
}
