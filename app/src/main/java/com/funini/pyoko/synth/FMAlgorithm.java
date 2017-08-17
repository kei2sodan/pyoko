package com.funini.pyoko.synth;

import java.util.ArrayList;
import java.util.List;

import static com.funini.pyoko.Consts.N_OPERATORS;

/**
 * Created by Kei on 2017/08/15.
 */

public class FMAlgorithm extends Operator {

    FMOperator[] mOpList = new FMOperator[N_OPERATORS];
    List<FMOperator> mOpOutputs = new ArrayList<FMOperator>();

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
    public void next(float stepRate) {
        for(FMOperator op: mOpList) {
            op.next();
        }
        mValue = 0;
        for(FMOperator op: mOpOutputs) {
            mValue += op.getValue();
        }
        mValue /=    mOpOutputs.size();
    }

    public void setAlgorithm(int alg) {
        mOpOutputs.clear();
        switch(alg){
            case 0:
                mOpOutputs.add(mOpList[0]);
                mOpList[0].setMod(mOpList[1]);
                mOpList[1].setMod(mOpList[2]);
                mOpList[2].setMod(mOpList[3]);
                mOpList[3].setMod();
                break;
            case 1:
                mOpOutputs.add(mOpList[0]);
                mOpList[0].setMod(mOpList[1]);
                mOpList[1].setMod(mOpList[2], mOpList[3]);
                mOpList[2].setMod();
                mOpList[3].setMod();
                break;
            case 2:
                mOpOutputs.add(mOpList[0]);
                mOpList[0].setMod(mOpList[1], mOpList[3]);
                mOpList[1].setMod(mOpList[2]);
                mOpList[2].setMod();
                mOpList[3].setMod();
                break;
            case 3:
                mOpOutputs.add(mOpList[0]);
                mOpOutputs.add(mOpList[2]);
                mOpList[0].setMod(mOpList[1]);
                mOpList[1].setMod();
                mOpList[2].setMod(mOpList[3]);
                mOpList[3].setMod();
                break;
            case 4:
                mOpOutputs.add(mOpList[0]);
                mOpOutputs.add(mOpList[1]);
                mOpOutputs.add(mOpList[2]);
                mOpList[0].setMod(mOpList[3]);
                mOpList[1].setMod(mOpList[3]);
                mOpList[2].setMod(mOpList[3]);
                mOpList[3].setMod();
                break;
            case 5:
                mOpOutputs.add(mOpList[0]);
                mOpOutputs.add(mOpList[1]);
                mOpOutputs.add(mOpList[2]);
                mOpList[0].setMod();
                mOpList[1].setMod();
                mOpList[2].setMod(mOpList[3]);
                mOpList[3].setMod();
                break;
            case 6:
                mOpOutputs.add(mOpList[0]);
                mOpOutputs.add(mOpList[1]);
                mOpOutputs.add(mOpList[2]);
                mOpOutputs.add(mOpList[3]);
                mOpList[0].setMod();
                mOpList[1].setMod();
                mOpList[2].setMod();
                mOpList[3].setMod();
                break;
        }

    }

    public FMOperator getOperator(int i) {
        return mOpList[i];
    }
}
