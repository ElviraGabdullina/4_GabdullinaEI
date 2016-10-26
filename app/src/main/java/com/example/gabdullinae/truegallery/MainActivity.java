package com.example.gabdullinae.truegallery;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private LruCache<Integer, Bitmap> mMemoryCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        RecyclerView rvImage = (RecyclerView) findViewById(R.id.recyclerView);

        mMemoryCache = new LruCache<Integer, Bitmap>(maxMemory) {
            @Override
            protected int sizeOf(Integer key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };

        addBitmapToMemoryCache(0, BitmapFactory.decodeResource(getResources(),R.drawable.pc1));
        addBitmapToMemoryCache(1, BitmapFactory.decodeResource(getResources(),R.drawable.pc2));
        addBitmapToMemoryCache(2, BitmapFactory.decodeResource(getResources(),R.drawable.pc3));
        addBitmapToMemoryCache(3, BitmapFactory.decodeResource(getResources(),R.drawable.pc4));
        addBitmapToMemoryCache(4, BitmapFactory.decodeResource(getResources(),R.drawable.pc5));
        addBitmapToMemoryCache(5, BitmapFactory.decodeResource(getResources(),R.drawable.pc6));
        addBitmapToMemoryCache(6, BitmapFactory.decodeResource(getResources(),R.drawable.pc7));



        ImageAdapter adapter = new ImageAdapter(this, mMemoryCache);
        rvImage.setAdapter(adapter);
        rvImage.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setItemClickListener(this);


    }

    public void addBitmapToMemoryCache(Integer key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(Integer key) {
        return mMemoryCache.get(key);
    }

    @Override
    public void onItemClick(Bitmap image) {
        Intent intent = new Intent(this, ImageActivity.class);
        if (image == getBitmapFromMemCache(0)){
            intent.putExtra("imageID",R.drawable.p1);
            startActivity(intent);
        } else   if (image == getBitmapFromMemCache(1)) {
            intent.putExtra("imageID", R.drawable.p2);
            startActivity(intent);
        }
        else   if (image == getBitmapFromMemCache(2)) {
            intent.putExtra("imageID", R.drawable.p3);
            startActivity(intent);
        }
        else   if (image == getBitmapFromMemCache(3)) {
            intent.putExtra("imageID", R.drawable.p4);
            startActivity(intent);
        }
        else   if (image == getBitmapFromMemCache(4)) {
            intent.putExtra("imageID", R.drawable.p5);
            startActivity(intent);
        }
        else   if (image == getBitmapFromMemCache(5)) {
            intent.putExtra("imageID", R.drawable.p6);
            startActivity(intent);
        }else  if (image == getBitmapFromMemCache(6)) {
            intent.putExtra("imageID", R.drawable.p7);
            startActivity(intent);
        }
    }
}
