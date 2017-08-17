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
    SeekBar mSeekBarSinLevel;
    SeekBar mSeekBarSawLevel;
    SeekBar mSeekBarSquareLevel;
    SeekBar mSeekBarNoiseLevel;
    SeekBar mSeekBarAttackRate;
    SeekBar mSeekBarDecayRate;
    SeekBar mSeekBarSustainRate;
    SeekBar mSeekBarReleaseRate;
    SeekBar mSeekBarSustainLevel;
    SeekBar mSeekBarTotalLevel;
    SeekBar mSeekBarFreqRatio;
    SeekBar mSeekBarFreqOffset;
    SeekBar mSeekBarFeedbackLevel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.operator, container, false);
    }

    public void setOperator(FMOperator op) {
        mOperator = op;
    }

    SeekBar setupSeekBar(int rid) {
        SeekBar ret = (SeekBar) (getView().findViewById(rid));
        ret.setOnSeekBarChangeListener(this);
        ret.setProgress(ret.getProgress());
        return ret;
    }

    public void onStart() {
        super.onStart();
        mSeekBarSinLevel= setupSeekBar(R.id.sb_sin);
        mSeekBarSawLevel = setupSeekBar(R.id.sb_saw);
        mSeekBarSquareLevel = setupSeekBar(R.id.sb_square);
        mSeekBarNoiseLevel = setupSeekBar(R.id.sb_noise);
        mSeekBarAttackRate = setupSeekBar(R.id.sb_attack_rate);
        mSeekBarDecayRate = setupSeekBar(R.id.sb_decay_rate);
        mSeekBarSustainRate = setupSeekBar(R.id.sb_sustain_rate);
        mSeekBarReleaseRate = setupSeekBar(R.id.sb_release_rate);
        mSeekBarSustainLevel = setupSeekBar(R.id.sb_sustain_level);
        mSeekBarTotalLevel = setupSeekBar(R.id.sb_total_level);
        mSeekBarFreqRatio = setupSeekBar(R.id.sb_freq_ratio);
        mSeekBarFreqOffset = setupSeekBar(R.id.sb_freq_offset);
        mSeekBarFeedbackLevel = setupSeekBar(R.id.sb_feedback_level);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (mOperator == null) {
            Log.e(Consts.TAG, "operator is null");
            return;
        }
        float v = (float) seekBar.getProgress() / seekBar.getMax();
        if(seekBar == mSeekBarSinLevel) {
            mOperator.setSinLevel(v);
        } else if (seekBar == mSeekBarSawLevel) {
            mOperator.setSawLevel(v);
        } else if (seekBar == mSeekBarSquareLevel) {
            mOperator.setSquareLevel(v);
        } else if (seekBar == mSeekBarNoiseLevel) {
            mOperator.setNoiseLevel(v);
        } else if (seekBar == mSeekBarAttackRate) {
            mOperator.setAttackRate(v);
        } else if (seekBar == mSeekBarDecayRate) {
            mOperator.setDecayRate(v);
        } else if (seekBar == mSeekBarSustainRate) {
            mOperator.setSustainRate(v);
        } else if (seekBar == mSeekBarReleaseRate) {
            mOperator.setReleaseRate(v);
        } else if (seekBar == mSeekBarSustainLevel) {
            mOperator.setSustainLevel(v);
        } else if (seekBar == mSeekBarTotalLevel) {
            mOperator.setTotalLevel(v);
        } else if (seekBar == mSeekBarFreqRatio) {
            mOperator.setFreqRatio(v);
        } else if (seekBar == mSeekBarFreqOffset) {
            mOperator.setFreqOffset(v);
        } else if (seekBar == mSeekBarFeedbackLevel) {
            mOperator.setFeedbackLevel(v);
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
}
