package com.seif.silentmosque;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.seif.silentmosque.provider.GPSProvider;
import com.seif.silentmosque.provider.PlacesFetcher;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    // Constants
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final int PERMISSIONS_MULTIPLE_REQUEST = 12;


    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getResources().getBoolean(R.bool.isTablet)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        checkAndroidVersion();
        Log.i("MainActivity", "onCreate");
        mClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, this)
                .build();

        GPSProvider gpsProvider = new GPSProvider(this);

        Log.i("MainActivity","LOCATION:" + gpsProvider.Latitude_lastRead + "," + gpsProvider.Longitude__lastRead);
        PlacesFetcher placesFetcher = new PlacesFetcher(this);
       placesFetcher.setLat_(gpsProvider.Latitude_lastRead);
        placesFetcher.setLng_(gpsProvider.Longitude__lastRead);
        try {
           // placesFetcher.start();

        } catch (Exception e) {
           Log.e("MainActivity",e.getMessage());
        }
        //  mGeofencing = new Geofencing(this, mClient);

    }

    /***
     * Called when the Google API Client is successfully connected
     *
     * @param connectionHint Bundle of data provided to clients by Google Play services
     */
    @Override
    public void onConnected(@Nullable Bundle connectionHint) {
        refreshPlacesData();
        Log.i(TAG, "API Client Connection Successful!");
    }

    /***
     * Called when the Google API Client is suspended
     *
     * @param cause cause The reason for the disconnection. Defined by constants CAUSE_*.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "API Client Connection Suspended!");
    }

    /***
     * Called when the Google API Client failed to connect to Google Play Services
     *
     * @param result A ConnectionResult that can be used for resolving the error
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.e(TAG, "API Client Connection Failed!");
    }

    public void refreshPlacesData() {

    }



    /***
     * Called when the Place Picker Activity returns back with a selected place (or after canceling)
     *
     * @param requestCode The request code passed when calling startActivityForResult
     * @param resultCode  The result code specified by the second activity
     * @param data        The Intent that carries the result data.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            Place place = PlacePicker.getPlace(this, data);
            if (place == null) {
                Log.i(TAG, "No place selected");
                return;
            }

            // Extract the place information from the API
            String placeName = place.getName().toString();
            String placeAddress = place.getAddress().toString();
            String placeID = place.getId();

            // Insert a new place into DB
            //ContentValues contentValues = new ContentValues();
            //contentValues.put(PlaceContract.PlaceEntry.COLUMN_PLACE_ID, placeID);
            //getContentResolver().insert(PlaceContract.PlaceEntry.CONTENT_URI, contentValues);

            // Get live data information
            refreshPlacesData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }



    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("insideMosque");
        sendBroadcast(intent);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i("MainActivity", "onPostResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");

    }


    public void addMosque(View view) {
        //view ->  text view available only if user inside mosque and app could not detect that
        //this action open mosque info fragment
        MosqueInfoFragment updateInfoFragment = new MosqueInfoFragment(false);
        if(this.findViewById(R.id.fragment_container)!=null){
            // Add the fragment to its container using a FragmentManager and a Transaction
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, updateInfoFragment)
                    .commit();
        }else{
            // Add the fragment to its container using a FragmentManager and a Transaction
            this.setContentView(R.layout.fragment_add_mosque_info );
        }


    }

    public void updateMosqueInfo(View view) {
        //view ->  text view available only if user inside mosque and app could detect that
        // this action open mosque info fragment but location listen is off
        // only mosque name update and keeper phone number will be available
    }

    public void cacheAroundMosques(int long_, int lat) {

    }

    private void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();

        } else {
            // write your logic here
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
                checkPermission();
                return;
            } else {
                Log.i("PERMISSIONS", "ALL GRANTED");
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {

                if (ActivityCompat.shouldShowRequestPermissionRationale
                        (this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale
                                (this, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                        ActivityCompat.shouldShowRequestPermissionRationale
                                (this, Manifest.permission.ACCESS_WIFI_STATE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale
                                (this, Manifest.permission.INTERNET) ||
                        ActivityCompat.shouldShowRequestPermissionRationale
                                (this, Manifest.permission.ACCESS_NETWORK_STATE)) {

                    Snackbar.make(this.findViewById(android.R.id.content),
                            "Please Grant Permissions",
                            Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @TargetApi(Build.VERSION_CODES.M)
                                @Override
                                public void onClick(View v) {
                                    requestPermissions(
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                                    Manifest.permission.ACCESS_WIFI_STATE,
                                                    Manifest.permission.INTERNET,
                                                    Manifest.permission.ACCESS_NETWORK_STATE},
                                            PERMISSIONS_MULTIPLE_REQUEST);
                                }
                            }).show();
                } else {
                    Log.i("PERMISSIONS", "ALL GRANTED");
                }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                if (grantResults.length > 0) {
                    boolean appPermissionACCESS_FINE_LOCATION = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean appPermissionACCESS_COARSE_LOCATION = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean appPermissionACCESS_WIFI_STATE = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean appPermissionINTERNET = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean appPermissionACCESS_NETWORK_STATE = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    if (appPermissionACCESS_FINE_LOCATION && appPermissionACCESS_COARSE_LOCATION && appPermissionACCESS_WIFI_STATE &&
                    appPermissionINTERNET && appPermissionACCESS_NETWORK_STATE) {
                      //  Toast.makeText(this,"Thanks for granst, hope to enjoy our service!",Toast.LENGTH_LONG).show();
                        Log.i("PERMISSIONS", "ALL GRANTED");
                    } else {

                        Snackbar.make(this.findViewById(android.R.id.content),
                                "Please Grant Permissions to be able to work, can't work without!",
                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                                new View.OnClickListener() {
                                    @TargetApi(Build.VERSION_CODES.M)
                                    @Override
                                    public void onClick(View v) {
                                        requestPermissions(
                                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                                        Manifest.permission.ACCESS_WIFI_STATE,
                                                        Manifest.permission.INTERNET,
                                                        Manifest.permission.ACCESS_NETWORK_STATE},
                                                PERMISSIONS_MULTIPLE_REQUEST);
                                    }
                                }).show();
                    }
                }
                break;
        }
    }
    public void sendMosqueInfo(View view) {
        // after checking new mosque info .. it will be sent to firebase database
    }

    public void getLocation(View view) {
        // to add new mosque and could be used to get around mosques (view == null)
    }

}
