package com.seif.silentmosque.provider;

import com.seif.silentmosque.model.Mosque;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPlacesDataService {

    @GET("bins/1bsqcn/")
    Call<List<Mosque>> getPlacesData();
}
