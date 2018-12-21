package com.seif.silentmosque.provider;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.seif.silentmosque.model.Mosque;

public abstract class PiWifiTalker {

    public int getWifiSignalStrength(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int numberOfLevels = 5;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
        if(level!=0){
            return level;
        }else{
            Log.e("getWifiSignalStrength","level: " + level);
            return 0;
        }
    }
    public void sentRequestforInfo(){

    }
    public Mosque getMosqueInfo(){
        Mosque mosque= null;

        return mosque;
    }

}
