package com.seif.silentmosque.provider;

import com.seif.silentmosque.model.Mosque;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPlacesDataService {

    @GET("bins/1bsqcn/")
    Call<Mosque> getPlaceData();

}
