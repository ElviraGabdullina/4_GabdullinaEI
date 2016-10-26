package com.example.gabdullinae.truegallery;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by gabdullinae on 21.10.2016.
 */

class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    OnItemClickListener imageListener;
    private Context context;
    private LruCache<Integer, Bitmap> mMemoryCache;

    public ImageAdapter(Context context, LruCache<Integer, Bitmap> mMemoryCache) {
        this.context = context;
        this.mMemoryCache = mMemoryCache;
    }

    private Context getContext() {
        return context;
    }

    public void setItemClickListener(OnItemClickListener imageListener) {
        this.imageListener = imageListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View imageView = inflater.inflate(R.layout.item_view, parent, false);
        final ViewHolder viewHolder = new ViewHolder(imageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(mMemoryCache.get(position % mMemoryCache.putCount()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageListener != null) {
                    imageListener.onItemClick(mMemoryCache.get(holder.getAdapterPosition() % mMemoryCache.putCount()));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return 50;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    interface OnItemClickListener {
        void onItemClick(Bitmap bitmap);
    }
}
