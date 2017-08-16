package com.funini.pyoko.synth;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;

import com.funini.pyoko.Consts;

/**
 * Created by Kei on 2017/08/16.
 */

public class AndroidPlayer {
    int mBufferSize;
    private AudioTrack track;
    private short[] S;
    Operator mOp;
    Thread thread;

    public AndroidPlayer(Operator op){
        mOp = op;
        initSnd();
    }


    public void terminate(){
        thread = null;
        track.stop();
        track.release();
    }

    public void start(){
        thread = new Thread(runnable);
        thread.start();
    }

    public void stop(){
        setStopFlag();
    }


    void initSnd(){
        thread = null;
        mBufferSize = AudioTrack.getMinBufferSize(Consts.SAMPLING_RATE,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        track = new AudioTrack.Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAudioFormat(new AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(44100)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build())
                .setBufferSizeInBytes(mBufferSize)
                .build();
        S = new short[mBufferSize];
        track.play();
    }

    Runnable runnable = new Runnable(){
        public void run() {
            while (!getStopFlag()) {
                for (int i = 0; i < mBufferSize; i++) {
                    float v = mOp.getValue();
                    S[i] = (short)(v * Short.MAX_VALUE);
                    mOp.next();
                }
                track.write(S, 0, S.length);
            }
        }
    };

    protected synchronized void setStopFlag(){
        thread = null;
    }

    protected synchronized boolean getStopFlag(){
        return thread == null;
    }
}
