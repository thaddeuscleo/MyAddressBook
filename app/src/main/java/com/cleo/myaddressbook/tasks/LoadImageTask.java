package com.cleo.myaddressbook.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoadImageTask {
    private final ImageView thumbnail;
    private final Executor executor;
    private final Handler handler;

    public LoadImageTask(ImageView thumbnail) {
        this.thumbnail = thumbnail;
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void execute(String url) {
        executor.execute(() -> {
            final Bitmap temp;
            InputStream inputStream;
            try {
                inputStream = new URL(url).openStream();
                temp = BitmapFactory.decodeStream(inputStream);
                handler.post(() -> {
                    thumbnail.setImageBitmap(temp);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
