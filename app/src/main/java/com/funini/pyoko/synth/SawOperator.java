package com.funini.pyoko.synth;

/**
 * Created by Kei on 2017/08/15.
 */

public class SawOperator extends Operator {
    float mPhase;

    public SawOperator(){
        init();
    }

    @Override
    public void init() {
        mPhase = 0;
        next(1.0f);
    }

    @Override
    public void next(float stepRate) {
        mPhase += mStep * stepRate;
        if(mPhase >= 1){
            mPhase -= 1;
        }
        mValue = (mPhase * 2.0f - 1.0f) * getVolume();
    }
}
