package com.example.gabdullinae.truegallery;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by gabdullinae on 26.10.2016.
 */
public class ImageActivity extends AppCompatActivity {
    ImageView image;
    PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image_layout);

        Intent intent = getIntent();
        int imageID = intent.getIntExtra("imageID", 0);
        image = ((ImageView) findViewById(R.id.imageFullView));
        image.setImageBitmap(BitmapFactory.decodeResource(getResources(), imageID));
        mAttacher = new PhotoViewAttacher(image);
    }
}
