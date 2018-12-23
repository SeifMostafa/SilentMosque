package com.seif.silentmosque.provider;

import com.seif.silentmosque.model.Mosque;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("bins/1bsqcn/")
    Call<List<Mosque>> getPlacesData();
}
