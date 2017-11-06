package com.xProject.XosoVIP.sdk.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.xProject.XosoVIP.R;

/**
 * Created by xtel on 11/6/17.
 */

public class AudioPlay {
    private static final AudioPlay ourInstance = new AudioPlay();
    private MediaPlayer mMediaPlayer;

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

        mMediaPlayer = MediaPlayer.create(c, R.raw.notification11);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
            }
        });

        mMediaPlayer.start();
    }
}
