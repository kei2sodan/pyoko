package com.funini.pyoko.synth;

import static com.funini.pyoko.Consts.N_OPERATORS;

/**
 * Created by Kei on 2017/08/15.
 */

public class FMAlgorithm extends Operator {

    FMOperator[] mOpList = new FMOperator[N_OPERATORS];

    public FMAlgorithm(){
        for(int i = 0; i < N_OPERATORS; i++){
            mOpList[i] = new FMOperator();
        }
        setVolume(1.0f);
    }


    @Override
    public synchronized void noteOn() {
        for(int i = 0; i < N_OPERATORS; i++) {
            mOpList[i].noteOn();
        }
    }

    @Override
    public synchronized void noteOff() {
        for(int i = 0; i < N_OPERATORS; i++) {
            mOpList[i].noteOff();
        }
    }

    public void setHz(float hz){
        for(int i = 0; i < N_OPERATORS; i++) {
            mOpList[i].setHz(hz);
        }
    }


    @Override
    public synchronized void next(float stepRate) {
        for(FMOperator op: mOpList) {
            op.next();
        }
        mValue = mOpList[N_OPERATORS-1].getValue();
    }

    public void setAlgorithm(int alg) {
        switch(alg){
            case 0:
                mOpList[3].setMod(mOpList[2]);
                mOpList[2].setMod(mOpList[1]);
                mOpList[1].setMod(mOpList[0]);
                mOpList[0].setMod();
                break;
            case 1:
                mOpList[3].setMod(mOpList[2]);
                mOpList[2].setMod(mOpList[1], mOpList[0]);
                mOpList[1].setMod();
                mOpList[0].setMod();
                break;
            case 2:
                mOpList[3].setMod(mOpList[2], mOpList[0]);
                mOpList[2].setMod(mOpList[1]);
                mOpList[1].setMod();
                mOpList[0].setMod();
                break;
            case 3:
                mOpList[3].setMod(mOpList[2], mOpList[1]);
                mOpList[2].setMod();
                mOpList[1].setMod(mOpList[0]);
                mOpList[0].setMod();
                break;
        }

    }

    public FMOperator getOperator(int i) {
        return mOpList[i];
    }
}
