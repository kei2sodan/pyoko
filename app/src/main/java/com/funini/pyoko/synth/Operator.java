package com.funini.pyoko.synth;

import com.funini.pyoko.Consts;

/**
 * FM Operator interface for generating wave form
 */

public abstract class Operator {
    float mVolume = 1.0f;
    float mHz;
    /* how many phase should it proceed for one step */
    float mStep;
    float mValue;

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

    public void setHz(float hz) {
        mHz = hz;
        mStep = (float)hz / Consts.SAMPLING_RATE;
    }


    public void setVolume(float vol){
        mVolume = vol;
    }

    public float getVolume(){
        return mVolume;
    }
}

