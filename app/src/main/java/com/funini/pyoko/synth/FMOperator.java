package com.funini.pyoko.synth;

/**
 * Created by Kei on 2017/08/15.
 */

public class FMOperator extends Operator {
   // Operator mSin = new SinOperator();
    Operator mSaw = new SawOperator();
    Operator mSquare = new SquareOperator();
    Operator mNoise = new NoiseOperator();
    Operator mMod0, mMod1;

    public FMOperator(Operator mod){
        mMod0 = mod;
    }

    public FMOperator(Operator mod0, Operator mod1){
        mMod0 = mod0;
        mMod1 = mod1;
    }

    public FMOperator(){
    }


    @Override
    public void init() {

    }

    public void setHz(float hz){
        //mSin.setHz(hz);
        mSaw.setHz(hz);
        mSquare.setHz(hz);
        mNoise.setHz(hz);
    }


    @Override
    public void next(float stepRate) {
        mSaw.next(stepRate);
        mSquare.next(stepRate);
        mNoise.next(stepRate);
        float v = /*mSin.getValue() + */ mSquare.getValue() + mNoise.getValue() + mSaw.getValue();
        mValue = v * 0.333f * getVolume();
    }

//    public void setSinLevel(float v) {
//        mSin.setVolume(v);
//    }

    public void setSawLevel(float v) {
        mSaw.setVolume(v);
    }

    public void setSquareLevel(float v) {
        mSquare.setVolume(v);
    }

    public void setNoiseLevel(float v) {
        mNoise.setVolume(v);
    }

    public void setAttackRate(float v) {
    }

    public void setDecayRate(float v) {
    }

    public void setSustainRate(float v) {
    }

    public void setReleaseRate(float v) {
    }

    public void setSustainLevel(float v) {
    }

    public void setTotalLevel(float v) {
        setVolume(v);
    }

}
