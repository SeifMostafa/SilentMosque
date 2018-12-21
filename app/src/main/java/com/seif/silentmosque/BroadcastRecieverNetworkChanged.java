package com.seif.silentmosque;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastRecieverNetworkChanged extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getCharSequenceExtra("insideUndefindMosque")=="insideUndefindMosque"){
            Log.i("Broad..:undefind","UNDEFIND");
        }
        Toast.makeText(context,"you are connected now!",Toast.LENGTH_LONG).show();
    }
}
