package com.seif.silentmosque.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.seif.silentmosque.model.Mosque;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesFetcher implements Callback<List<Mosque>> {
    private static final double AREA_AROUND_2CACHE = 5000;
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
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
        APIService getAroundPlaces = RetrofitClient.getClient(BASE_URL + "location=" + this.lat_ + "," + this.lng_+
                "&radius=" + AREA_AROUND_2CACHE+
                "&type=" + "mosque"+
                "&key=" + context.getPackageManager().getApplicationInfo(context.getPackageName(),
                PackageManager.GET_META_DATA).metaData.getString("com.google.android.geo.API_KEY")+"/").create(APIService.class);
        getAroundPlaces.getPlacesData().enqueue(new Callback<List<Mosque>>() {
            @Override
            public void onResponse(Call<List<Mosque>> call, Response<List<Mosque>> response) {
                for(Mosque mosque:response.body()){
                    Log.i("PlacesFetcher:start",mosque.toString());
                }
                Log.i("PlacesFetcher:success","no more");

            }

            @Override
            public void onFailure(Call<List<Mosque>> call, Throwable t) {
                Log.e("PlacesFetcher:Fail",t.getMessage());

            }
        });

    }


    @SuppressLint("NewApi")
    @Override
    public void onResponse(Call<List<Mosque>> call, Response<List<Mosque>> response) {
        if (response.isSuccessful()) {
            List<Mosque> changesList = response.body();
            for(Mosque mosque:changesList){
                Log.i("onResponse", mosque.toString());
            }
        } else {
            Log.e("onResponse", "" + response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Mosque>> call, Throwable t) {
        Log.e("onResponse", "" + t.getMessage());

    }
}
