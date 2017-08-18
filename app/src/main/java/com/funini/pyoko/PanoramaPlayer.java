package com.funini.pyoko;

/**
 * Created by Kei on 2017/08/18.
 */

public class PanoramaPlayer {
    //http://tomari.org/main/java/oto.html
    final static double KEY_BASE = Math.log(2)/12;
    //final int[][] = {20,11,16,20,18,11,15,18,16,9,13,16,};
    final static int[] HORN = {11,20,16,11,20,16,11,20,11,16};
    final static float[] HORN_FREQS = new float[HORN.length];

    final static int NOTEON_COUNT = 15000;
    final static int NOTEOFF_COUNT = 3000;
    final static int NOTE_COUNT = NOTEON_COUNT + NOTEOFF_COUNT;
    int mCount = 0;
    boolean mNoteOn = false;
    boolean mNoteOff = false;

    static {
        for(int i = 0; i < HORN.length; i++){
            double pow = HORN[i];
            HORN_FREQS[i] = 220f  * (float)Math.exp(KEY_BASE * pow);
        }
    }


    public PanoramaPlayer(){
        mCount = -1;
    }

    public void init(){
        mCount = 0;
        mNoteOn = true;
        mNoteOff = false;
    }

    public void next(){
        mNoteOn = false;
        mNoteOff = false;
        if(mCount < 0){
            return;
        }
        mCount++;
        if(mCount == (HORN.length + 1) * NOTE_COUNT + NOTEON_COUNT){
            mCount = -1;
            mNoteOff = true;
            return;
        }
        if(mCount % NOTE_COUNT == 0){
            int noteCount = mCount / NOTE_COUNT;
            if(noteCount < HORN.length){
                mNoteOn = true;
            }
        }
        if(mCount % NOTE_COUNT == NOTEON_COUNT){
            int noteCount = mCount / NOTE_COUNT;
            if(noteCount < HORN.length - 1){
                mNoteOff = true;
            }
        }
    }

    public boolean noteOn(){
        return mNoteOn;
    }

    public boolean noteOff(){
        return mNoteOff;
    }

    public float getFreq(){
        if(mCount < 0) {
            return 0;
        }
        int noteCount = mCount / NOTE_COUNT;
        if(noteCount >= HORN_FREQS.length) {
            noteCount = HORN_FREQS.length - 1;
        }
        return HORN_FREQS[noteCount];
    }
}
