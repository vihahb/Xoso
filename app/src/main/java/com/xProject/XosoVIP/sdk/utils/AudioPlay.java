package com.xProject.XosoVIP.sdk.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import com.xProject.XosoVIP.R;

/**
 * Created by xtel on 11/6/17.
 */

public class AudioPlay {
    private static final String TAG = "AudioPlay";
    
    private static final AudioPlay ourInstance = new AudioPlay();
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private AudioPlay() {
    }

    public static AudioPlay getInstance() {
        return ourInstance;
    }

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void play(Context c) {
        stop();
        mAudioManager = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
        mMediaPlayer = MediaPlayer.create(c, R.raw.notification11);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
            }
        });
        Log.e(TAG, "Ring mode: " + getRingerModeString());
        if (getRingerModeString().equals("Normal")){
            mMediaPlayer.start();
        }
    }

    protected String getRingerModeString(){
        /*
            public int getRingerMode ()
                Returns the current ringtone mode.

            Returns
                The current ringtone mode, one of RINGER_MODE_NORMAL, RINGER_MODE_SILENT,
                or RINGER_MODE_VIBRATE.
        */

        int modeConstantValue = mAudioManager.getRingerMode();
        String ringerModeString = "";
        if(modeConstantValue == 0){
        /*
            public static final int RINGER_MODE_SILENT
                Ringer mode that will be silent and will not vibrate.
                (This overrides the vibrate setting.)

                Constant Value: 0 (0x00000000)
        */
            ringerModeString = "Silent";
        } else if(modeConstantValue == 1){
            /*
                public static final int RINGER_MODE_VIBRATE
                    Ringer mode that will be silent and will vibrate. (This will cause the phone
                    ringer to always vibrate, but the notification vibrate to only vibrate if set.)

                    Constant Value: 1 (0x00000001)
            */
            ringerModeString = "Vibrate";
        }else if(modeConstantValue == 2){
            /*
                public static final int RINGER_MODE_NORMAL
                    Ringer mode that may be audible and may vibrate. It will be audible if the
                    volume before changing out of this mode was audible. It will vibrate if the
                    vibrate setting is on.

                    Constant Value: 2 (0x00000002)
            */
            ringerModeString = "Normal";
        }

        // Return the ringer mode as string
        return ringerModeString;
    }
}
