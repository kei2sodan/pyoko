package com.funini.pyoko.synth;

import com.funini.pyoko.Consts;

/**
 * FM Operator interface for generating wave form
 */

public abstract class Operator {
    volatile float mVolume = 0.0f;
    float mHz;
    float mFreqRatio = 1.0f;
    /* how many phase should it proceed for one step */
    float mStep;
    float mValue;
    boolean mNoteOn = false;

    public void noteOn(){
        mNoteOn = true;
    }

    public void noteOff(){
        mNoteOn = false;
    }

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

    public synchronized void setHz(float hz) {
        mHz = hz;
        calcStep();
    }

    public synchronized float getHz() {
        return mHz;
    }

    public synchronized void setVolume(float vol){
        mVolume = vol;
    }

    public synchronized float getVolume(){
        return mVolume;
    }

    public synchronized void setFreqRatio(float v) {
        mFreqRatio = (float)Math.pow(2.0, (v -  0.5) * 4);
        calcStep();
    }

    public synchronized float getFreqRatio() {
        return mFreqRatio;
    }

    protected void calcStep(){
        mStep = getHz() * getFreqRatio() / Consts.SAMPLING_RATE;
    }
}
