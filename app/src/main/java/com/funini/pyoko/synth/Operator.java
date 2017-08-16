package com.funini.pyoko.synth;

import com.funini.pyoko.Consts;

/**
 * FM Operator interface for generating wave form
 */

public abstract class Operator {
    volatile float mVolume = 1.0f;
    volatile float mHz;
    /* how many phase should it proceed for one step */
    volatile float mStep;
    volatile float mValue;

    public abstract void init();

    /**
     * @return ope value in the range of 0 < val < 1
     */
    public float getValue() {
        return mValue;
    }

    /**
     * @param stepRate Proceed the phase by the value (0.5 < step < 2 while step=1 stands for normal time scale)
     */
    public abstract void next(float stepRate);

    public void next(){
        next(1.0f);
    }

    public void setHz(float hz) {
        mHz = hz;
        mStep = (float)hz / Consts.SAMPLING_RATE;
    }

    public synchronized void setVolume(float vol){
        mVolume = vol;
    }

    public synchronized float getVolume(){
        return mVolume;
    }
}
