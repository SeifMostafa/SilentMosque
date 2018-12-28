package com.seif.silentmosque.fcm;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.seif.silentmosque.model.Mosque;

import java.util.ArrayList;
import java.util.List;

public class MosqueDataParseAdapter {

    private static final String TAG = "MosqueDataParseAdapter";

    public boolean parseMosque(Mosque mosque) {
        // Write a mosque to the database
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference mosquesRef = database.getReference("/mosques");
            //DatabaseReference centerRef = database.getReference("mosque/location/center_loc");


            mosquesRef.child(mosque.getName()).child("loc").setValue(mosque.getCenter_loc());
            mosquesRef.child(mosque.getName()).child("r").setValue(mosque.getR());
            mosquesRef.child(mosque.getName()).child("keeperPhoneNum").setValue(mosque.getKeeperPhoneNum());

            Log.i("parseMosque", "DATA POSTED");
            return true;
        } catch (Exception e) {
            Log.e("parseMosque", e.getMessage());
            return false;
        }

    }

    public List<Mosque> retriveMosques() {
        List<Mosque> mosquesList = new ArrayList<Mosque>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference mosquesRef = database.getReference("/mosques");
        // download database from firebase if ourlocation is nearby..
        // compare ref.loc with our loc..

        return mosquesList;
    }

}
