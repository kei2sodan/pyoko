package com.funini.pyoko.synth;

import android.util.Log;

import com.funini.pyoko.Consts;

import java.util.Random;

/**
 * Created by Kei on 2017/08/15.
 */

public class NoiseOperator extends Operator {
    Random mRand = new Random();

    public NoiseOperator() {
        noteOn();
    }

    @Override
    public void noteOn() {
        mValue = 0;
    }

    @Override
    public void next(float v) {
        mValue = mRand.nextFloat() * getVolume();
    }

    @Override
    public synchronized void setVolume(float vol) {
        super.setVolume(vol);
        Log.e(Consts.TAG, "vol:" + getVolume());
    }
}
