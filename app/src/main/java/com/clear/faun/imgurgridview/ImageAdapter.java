package com.clear.faun.imgurgridview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by spencer on 7/29/2015.
 */
public class ImageAdapter  extends BaseAdapter {
    private Context mContext;
    ArrayList<String> imgurURL;
    // Constructor
    public ImageAdapter(Context c, ArrayList<String> imgurURL) {
        mContext = c;
        this.imgurURL = imgurURL;
    }

    public int getCount() {
        return imgurURL.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    Inflater inflater;

    @Override
    public View getView(int position, View recycled, ViewGroup container) {
        Log.d("ImageAdapter", "getView()");
        final ImageView myImageView;
        if (recycled == null) {
            myImageView = new ImageView(mContext);
        } else {
            myImageView = (ImageView) recycled;
        }


        int width = getScreenWidth();

        width = (width - 30) / 2;

        Log.d("ImageAdapter", "screen width :)" + width);

        String url = imgurURL.get(position);

        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .crossFade()
                .override(width, width)
                .into(myImageView);

        return myImageView;
    }

    public int getScreenWidth(){
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

}