package com.seif.silentmosque.provider;

import com.seif.silentmosque.model.Mosque;

import retrofit2.Call;

public class PlacesFetcher extends RetrofitInstance implements GetPlacesDataService {
    @Override
    public Call<Mosque> getPlaceData() {

        return null;
    }
}
