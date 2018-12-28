package com.seif.silentmosque.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seif.silentmosque.model.Mosque;
import com.seif.silentmosque.model.MosqueList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * controller mosque fetching
 */
public class PlacesFetcher implements Callback<MosqueList> {
    private static final double AREA_AROUND_2CACHE = 5000;
    private static final String BASE_URL = "https://maps.googleapis.com/";
    double lat_;
    double lng_;
    Context context;


    public PlacesFetcher(Context mainContext) {
        context = mainContext;
    }

    public void setLat_(double lat_) {
        this.lat_ = lat_;
    }

    public void setLng_(double lng_) {
        this.lng_ = lng_;
    }

    public void start() throws PackageManager.NameNotFoundException {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        GetPlacesDataService getAroundPlaces = retrofit.create(GetPlacesDataService.class);
        String keyParam = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                PackageManager.GET_META_DATA).metaData.getString("com.google.android.geo.API_KEY");
        String locationParam = this.lat_ + "," + this.lng_;
        String radiusParam = "" + AREA_AROUND_2CACHE;
        String typeParam = "mosque";

        Call<MosqueList> call = getAroundPlaces.getPlacesData(keyParam, locationParam, radiusParam, typeParam);
        call.enqueue(this);

    }


    @SuppressLint("NewApi")
    @Override
    public void onResponse(Call<MosqueList> call, Response<MosqueList> response) {
        if (response.isSuccessful()) {
            MosqueList mosqueList = (MosqueList) response.body();

            if (mosqueList != null) {

                for (Mosque mosque : mosqueList.getMosques()) {
                    Log.i("onResponse", mosque.toString());
                }
            }

            Log.i("PlacesFetcher:success", "no more");

        } else {
            Log.e("onResponse", "" + response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<MosqueList> call, Throwable t) {
        Log.e("onFailure", "" + t.getMessage());


    }
}
