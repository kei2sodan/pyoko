package com.funini.pyoko.synth;

import com.funini.pyoko.Consts;

/**
 * Created by Kei on 2017/08/15.
 */

public class FMOperator extends Operator {
    Operator mSin = new SinOperator();
    Operator mSaw = new SawOperator();
    Operator mSquare = new SquareOperator();
    Operator mNoise = new NoiseOperator();
    Operator mMod0, mMod1;
    public static float MAX_ATTACK_RATE_SEC = 0.2f;
    public static float MAX_DECAY_RATE_SEC = 0.8f;
    public static float MAX_SUSTAIN_RATE_SEC = 10f;
    public static float MAX_RELEASE_RATE_SEC = 0.5f;

    int mAttackRate = 0; /* how many samples does it take to reach the attack (max: 0.1 sec) */
    int mDecayRate = 0; /* how many samples does it take to decay from the attack */
    int mSustainRate = 0;
    int mReleaseRate = 0;
    float mSustainLevel = 1.0f; /* how many level does it keep on decayed state (0 to 1) */
    float mReleasedLevel = 0;
    int mEnvelopCount = 0;

    float mEnvelop = 0;
    float mFeedbackLevel = 0;

    enum EnvelopMode {
        OFF,
        ATTACK,
        DECAY,
        SUSTAIN,
        RELEASE
    };
    EnvelopMode mEnvelopMode = EnvelopMode.OFF;

    public void setMod(Operator mod){
        mMod0 = mod;
        mMod1 = null;
    }

    public void setMod(Operator mod0, Operator mod1){
        mMod0 = mod0;
        mMod1 = mod1;
    }

    public void setMod(){
        mMod0 = mMod1 = null;
    }

    public FMOperator(){
    }



    @Override
    public void noteOn() {
        super.noteOn();
        mEnvelopCount = 0;
        mEnvelopMode = EnvelopMode.ATTACK;
    }

    @Override
    public void noteOff() {
        super.noteOn();
        mReleasedLevel = mEnvelop;
        mEnvelopCount = 0;
        mEnvelopMode = EnvelopMode.RELEASE;
    }

    public void setHz(float hz){
        mSin.setHz(hz);
        mSaw.setHz(hz);
        mSquare.setHz(hz);
        mNoise.setHz(hz);
    }


    @Override
    public void next(float _) {
        updateEnvelop();
        float stepRate = 1.0f;
        if(mMod0 != null) {
            stepRate += mMod0.getValue();
            if (mMod1 != null){
                stepRate += mMod1.getValue();
            }
        }
        stepRate += mFeedbackLevel * mValue;
        mSin.next(stepRate);
        mSaw.next(stepRate);
        mSquare.next(stepRate);
        mNoise.next(stepRate);
        float r = (mSin.getVolume() + mSquare.getVolume() + mSaw.getVolume() + mNoise.getVolume());
        if(r == 0){
            mValue = 0;
        } else {
            float v = mSin.getValue() + mSquare.getValue() + mNoise.getValue() + mSaw.getValue();
            mValue = v * getVolume() * getEnvelop() / r;
        }
    }

    private void updateEnvelop() {
        switch(mEnvelopMode){
            case ATTACK:
                if(mAttackRate > 0 && ++mEnvelopCount < mAttackRate){
                    mEnvelop = (float)mEnvelopCount / mAttackRate;
                } else {
                    mEnvelopMode = EnvelopMode.DECAY;
                    mEnvelopCount = 0;
                    mEnvelop = 1.0f;
                }
                break;
            case DECAY:
                if(mDecayRate > 0 && ++mEnvelopCount < mDecayRate){
                    mEnvelop = 1.0f - (1.0f - mSustainLevel) * (float)mEnvelopCount / mDecayRate;
                } else {
                    mEnvelopMode = EnvelopMode.SUSTAIN;
                    mEnvelopCount = 0;
                    mEnvelop = mSustainLevel;
                }
                break;
            case SUSTAIN:
                if(mSustainRate == 0){
                    mEnvelop = mSustainLevel;
                } else if(++mEnvelopCount < mSustainRate){
                    mEnvelop = mSustainLevel * (1.0f - (float)mEnvelopCount / mSustainRate);
                } else {
                    mEnvelopMode = EnvelopMode.OFF;
                    mEnvelopCount = 0;
                    mEnvelop = 0.0f;
                }
                break;
            case RELEASE:
                if(mReleaseRate > 0 && ++mEnvelopCount < mReleaseRate){
                    mEnvelop = mReleasedLevel * (1.0f - (float)mEnvelopCount / mReleaseRate);
                } else {
                    mEnvelopMode = EnvelopMode.OFF;
                    mEnvelopCount = 0;
                    mEnvelop = 0.0f;
                }
                break;
            case OFF:
                mEnvelopCount = 0;
                mEnvelop = 0.0f;
                break;
        }
        if(mEnvelop < 0){
            mEnvelop = 0;
            assert false;
        }
    }

    private float getEnvelop() {
        return mEnvelop;
    }

    public void setSinLevel(float v) {
        mSin.setVolume(v);
    }

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
        mAttackRate = (int)(Consts.SAMPLING_RATE * v * MAX_ATTACK_RATE_SEC);
    }

    public void setDecayRate(float v) {
        mDecayRate = (int)(Consts.SAMPLING_RATE * v * MAX_DECAY_RATE_SEC);
    }

    public void setSustainRate(float v) {
        mSustainRate = (int)(Consts.SAMPLING_RATE * v * MAX_SUSTAIN_RATE_SEC);
    }

    public void setReleaseRate(float v) {
        mReleaseRate  = (int)(Consts.SAMPLING_RATE * v * MAX_RELEASE_RATE_SEC);
    }

    public void setSustainLevel(float v) {
        mSustainLevel = v;
    }

    public void setTotalLevel(float v) {
        setVolume(v);
    }

    @Override
    public void setFreqRatio(float v) {
        mSin.setFreqRatio(v);
        mSaw.setFreqRatio(v);
        mSquare.setFreqRatio(v);
        mNoise.setFreqRatio(v);
    }

    public void setFreqOffset(float v) {
        mSin.setFreqOffset(v);
        mSaw.setFreqOffset(v);
        mSquare.setFreqOffset(v);
        mNoise.setFreqOffset(v);
    }

    public void setFeedbackLevel(float v) {
        mFeedbackLevel = v;
    }

}
