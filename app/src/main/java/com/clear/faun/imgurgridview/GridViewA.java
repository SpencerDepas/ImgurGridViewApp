package com.clear.faun.imgurgridview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by spencer on 7/29/2015.
 */
public class GridViewA extends Activity {


    private Context mContext;
    ArrayList<String> imgurURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_a);
        Log.d("GridViewA", "onCreate");

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.WHITE);

        mContext = getApplicationContext();
        Bundle b = getIntent().getExtras();

        if(b!=null){
            imgurURL = b.getStringArrayList("myArrayList");
            Log.d("GridViewA", "imgurURL.get 1 : " + imgurURL.get(0) );
        }

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(mContext, imgurURL));

    }


}