package com.funini.pyoko.synth;

/**
 * Created by Kei on 2017/08/15.
 */

public class SquareOperator extends Operator {
    float mPhase;

    public SquareOperator(){
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
        if(mPhase < 0.5f){
            mValue = -0.5f;
        } else {
            mValue = 0.5f;
        }
    }
}
