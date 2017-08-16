package com.funini.pyoko.synth;

import java.util.Random;

/**
 * Created by Kei on 2017/08/15.
 */

public class NoiseOperator extends Operator {
    Random mRand = new Random();

    public NoiseOperator() {
        init();
    }

    @Override
    public void init() {
        mValue = 0;
    }

    @Override
    public void next(float v) {
        mValue = mRand.nextFloat();
    }
}
