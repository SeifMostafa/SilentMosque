package com.seif.silentmosque.provider;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

public class MosqueAudioManager {
    AudioManager mAudioManager;
    Context context;


    public MosqueAudioManager(Context context) {
        this.context = context;
        mAudioManager= (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }
    public void turn2Silent(){
        try{
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }catch (Exception e){
            Log.e("turn2Silent",e.toString());
            Toast.makeText(context,"couldn't turn your phone to silent!",Toast.LENGTH_LONG).show();
        }
    }
    public void turn2Normal(){
        try{
            mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }catch (Exception e){
            Log.e("turn2Normal",e.toString());
            Toast.makeText(context,"couldn't turn your phone to normal!",Toast.LENGTH_LONG).show();
        }
    }
}
