package com.funini.pyoko.synth;

/**
 * Created by Kei on 2017/08/15.
 */

public class SawOperator extends Operator {
    float mPhase;

    public SawOperator(){
        noteOn();
    }

    @Override
    public void noteOn() {
        mPhase = 0;
        next(1.0f);
    }

    @Override
    public void next(float stepRate) {
        mPhase += mStep * stepRate;
        while(mPhase >= 1.0f){
            mPhase -= 1.0f;
        }
        while(mPhase < 0){
            mPhase += 1.0f;
        }
        mValue = (mPhase * 2.0f - 1.0f) * getVolume();
    }
}
