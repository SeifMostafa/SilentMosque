package com.seif.silentmosque.provider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.seif.silentmosque.model.Mosque;

public class PiWifiTalker {
    Context context;

    public PiWifiTalker(Context context) {
        this.context = context;
    }

    public int getWifiSignalStrength(){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int numberOfLevels = 10;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if(wifiInfo!=null) {
            int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
            if (level != 0) {
                return level;
            } else {
                Log.e("getWifiSignalStrength", "level: " + level);
                return 0;
            }
        }else return 0;
    }
    public void sentRequestforInfo(){

    }
    public Mosque getMosqueInfo(){
        Mosque mosque= null;

        return mosque;
    }
    public static String getCurrentSsid(Context context) {
        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && connectionInfo.getSSID()!="") {
                ssid = connectionInfo.getSSID();
                Log.i("getCurrentSsid",ssid);
            }
        }
        return ssid;
    }
}
