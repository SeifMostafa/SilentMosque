package com.seif.silentmosque;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MosqueInfoFragment extends Fragment {
    // if this boolean is true this fragment will make phone num and mosque name only
    // to be editable
    // else it's for creating new mosque
    boolean updateInfoOnly = false;
    double lat_,long_;
    public MosqueInfoFragment(){

    }
    @SuppressLint("ValidFragment")
    public MosqueInfoFragment(boolean openForUpdate) {
        updateInfoOnly = openForUpdate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_mosque_info,container,false);
        if(updateInfoOnly){
            FloatingActionButton floatingActionButton_getGPS_Current = (FloatingActionButton)rootView.findViewById(R.id.floatingActionButton_getlocation);
            floatingActionButton_getGPS_Current.setClickable(false);

            EditText editText_hintforpresslocation = (EditText)rootView.findViewById(R.id.editText_hintforpresslocation);
            editText_hintforpresslocation.setEnabled(false);
            editText_hintforpresslocation.setText("Already have the location");
            EditText editText_loc_boundaries = (EditText)rootView.findViewById(R.id.editText_hintforpresslocation);
            editText_loc_boundaries.setEnabled(false);
            editText_loc_boundaries.setText("Location:\n( " + this.lat_+", "+this.long_ +")");




        }

        return rootView;
    }
    public void setCurrentGPS_lat_long(double lat,double lng){
        this.lat_ = lat;
        this.long_ = lng;
    }


}
