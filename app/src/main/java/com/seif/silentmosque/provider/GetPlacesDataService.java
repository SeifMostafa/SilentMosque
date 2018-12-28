package com.seif.silentmosque.provider;

import com.seif.silentmosque.model.Mosque;
import com.seif.silentmosque.model.MosqueList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPlacesDataService {

    @GET("maps/api/place/nearbysearch/json?")

    Call<MosqueList> getPlacesData(@Query("key") String APIKey, @Query("location") String location, @Query("radius") String radius, @Query("type") String type);
}
