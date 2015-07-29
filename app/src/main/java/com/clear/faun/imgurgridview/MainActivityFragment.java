package com.clear.faun.imgurgridview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AsyncResponse{

    private Context mContext;

    public MainActivityFragment() {
    }

    ImageView imageView1;
    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    ParseImgurLink pImgure;




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("MainActivityFragment", "onActivityCreated()");


        mContext = getActivity().getApplicationContext();
        pImgure = new ParseImgurLink(getActivity().getApplicationContext().getString( R.string.API_KEY_IMGUR ));
        pImgure.delegate = this;
        pImgure.getImgurURL();



    }

    ArrayList<String> imgurURL;

    @Override
    public void processFinish(ArrayList<String> imgurURL) {
        Log.d("MainActivityFragment", "processFinish");

        Log.d("MainActivityFragment", "imgurURL.size() " + imgurURL.size());
        this.imgurURL = imgurURL;

        Intent intent = new Intent(mContext , GridViewA.class);
        intent.putStringArrayListExtra("myArrayList", imgurURL);
        startActivity(intent);
        getActivity().getSupportFragmentManager().popBackStack();


    }



}
