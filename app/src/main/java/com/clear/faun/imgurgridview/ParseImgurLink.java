package com.clear.faun.imgurgridview;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by spencer on 7/28/2015.
 */
public class ParseImgurLink {

    final String IMGUR_API_KEY;

    public ParseImgurLink(String apiKey){
        IMGUR_API_KEY = apiKey;
    }

    public void getImgurURL() {
        new LongOperation().execute("");
    }

    public AsyncResponse delegate=null;

    private class LongOperation extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... params) {

            try {

                //this is because we want the service to work independently from each other. so depending on what starts this class depends on how we get api key
                Log.i("ParseImgurLink", "before decides which URL  ");


                String imgurImageURL = "https://api.imgur.com/3/gallery/hot/viral/0.json";

                String redditnycstreetart = "https://api.imgur.com/3/gallery/r/nycstreetart/time/0.json";



                String clientId = IMGUR_API_KEY;



                URL url = new URL(redditnycstreetart);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //conn.setRequestProperty ("Authorization", "Client-ID=" + basicAuth);

                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                //conn.setRequestProperty("Client-ID", "3d5419ceff3c88f");
                conn.setRequestProperty("Authorization", "Client-ID " + clientId);
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                InputStream stream = conn.getInputStream();

                String data = convertStreamToString(stream);

                readAndParseJSON(data);
                stream.close();

            } catch (Exception e) {
                e.printStackTrace();
                Log.i("ParseImgurLink", "inside fetchBusStop inside exception " + e);

            }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("ParseImgurLink", "onPostExecute  ");

            Log.i("ParseImgurLink", "result = imgurURL.get(0):   " + imgurURL.get(0));

            delegate.processFinish(imgurURL);


        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }





    final int TOTALIMAGEREQUESTS = 14;
    private ArrayList<String> imgurURL = new ArrayList<>();

    public void readAndParseJSON(String in) {

        JSONObject reader = null;
        try {

            //Log.i("ParseImgurLink", "inside readAndParseJSON");
            reader = new JSONObject(in);


            JSONArray sys = reader.getJSONArray("data");
            //Log.i("ParseImgurLink", "sys" + sys.toString());

            for(int i = 0, z = 0; i < sys.length() && z < TOTALIMAGEREQUESTS; i ++){
                //Log.i("ParseImgurLink", "i " + i);
                JSONObject imgurLink= (JSONObject) sys.get(i);

                //Log.i("ParseImgurLink", "imgurLink.get(\"link\") " + imgurLink.get("link"));


                if(!imgurLink.get("link").toString().equals("null")) {
                    z++;
                    imgurURL.add(imgurLink.get("link").toString());
                    //Log.i("ParseImgurLink", "sys " + imgurLink.toString());
                    Log.i("ParseImgurLink", "link " + imgurLink.get("link"));
                }


            }








        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.i("ParseImgurLink", "inside readAndParseJSON  Exception  " + e.toString());
            Log.i("ParseImgurLink", "inside readAndParseJSON  reader  " + reader.toString());
        }

    }




    public ArrayList<String> getImgurURLList() {
        return imgurURL;
    }

    String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
