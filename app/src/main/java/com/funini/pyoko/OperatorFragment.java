package com.funini.pyoko;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.funini.pyoko.synth.FMOperator;

/**
 * Created by kei2s
 */

public class OperatorFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    FMOperator mOperator;
    //SeekBar mSeekBarSinLevel;
    SeekBar mSeekBarSawLevel;
    SeekBar mSeekBarSquareLevel;
    SeekBar mSeekBarNoiseLevel;
    SeekBar mSeekBarAttackRate;
    SeekBar mSeekBarDecayRate;
    SeekBar mSeekBarSustainRate;
    SeekBar mSeekBarReleaseRate;
    SeekBar mSeekBarSustainLevel;
    SeekBar mSeekBarTotalLevel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.operator, container, false);
    }

    public void setOperator(FMOperator op){
        mOperator = op;
    }

    SeekBar setupSeekBar(int rid){
        SeekBar ret = (SeekBar)(getView().findViewById(rid));
        ret.setOnSeekBarChangeListener(this);
        return ret;
    }

    public void onStart() {
        super.onStart();
        //mSeekBarSinLevel= setupSeekBar(R.id.sb_sin);
        mSeekBarSawLevel= setupSeekBar(R.id.sb_saw);
        mSeekBarSquareLevel= setupSeekBar(R.id.sb_square);
        mSeekBarNoiseLevel= setupSeekBar(R.id.sb_noise);
        mSeekBarAttackRate= setupSeekBar(R.id.sb_attack_rate);
        mSeekBarDecayRate= setupSeekBar(R.id.sb_decay_rate);
        mSeekBarSustainRate= setupSeekBar(R.id.sb_sustain_rate);
        mSeekBarReleaseRate= setupSeekBar(R.id.sb_release_rate);
        mSeekBarSustainLevel= setupSeekBar(R.id.sb_sustain_level);
        mSeekBarTotalLevel= setupSeekBar(R.id.sb_total_level);
        Log.e(Consts.TAG, "init operator fragment: ");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(mOperator == null){
            Log.e(Consts.TAG, "operator is null");
            return;
        }
        float v = (float)seekBar.getProgress() / seekBar.getMax();
        /*if(seekBar == mSeekBarSinLevel) {
            mOperator.setSinLevel(v);
        } else*/
        if(seekBar == mSeekBarSawLevel) {
            mOperator.setSawLevel(v);
        } else if (seekBar == mSeekBarSquareLevel) {
            mOperator.setSquareLevel(v);
        } else if (seekBar == mSeekBarNoiseLevel) {
            mOperator.setNoiseLevel(v);
            Log.e(Consts.TAG, "setNoise:" + v + ", mOp: " + mOperator + "," + this);
        } else if(seekBar == mSeekBarAttackRate) {
            mOperator.setAttackRate(v);
        } else if(seekBar == mSeekBarDecayRate) {
            mOperator.setDecayRate(v);
        } else if(seekBar == mSeekBarSustainRate) {
            mOperator.setSustainRate(v);
        } else if(seekBar == mSeekBarReleaseRate) {
            mOperator.setReleaseRate(v);
        } else if(seekBar == mSeekBarSustainLevel) {
            mOperator.setSustainLevel(v);
        } else if(seekBar == mSeekBarTotalLevel){
            mOperator.setTotalLevel(v);
        } else {
            Log.w(Consts.TAG, "Unknown seekbar");
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof MainActivity)) {
            throw new UnsupportedOperationException(
                    "Unknown activity");
        } else {
            setOperator(((MainActivity)context).getOperator(3));
        }
        Log.e(Consts.TAG, "init: " + this + ", getOperator" + mOperator);
    }
}
