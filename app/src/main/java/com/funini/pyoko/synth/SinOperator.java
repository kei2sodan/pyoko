package com.funini.pyoko.synth;

/**
 * Created by Kei on 2017/08/15.
 */

public class SinOperator extends Operator {
    float mPhase;

    public SinOperator(){
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
        mValue = (float)Math.sin(mPhase * (Math.PI * 2));
    }
}
