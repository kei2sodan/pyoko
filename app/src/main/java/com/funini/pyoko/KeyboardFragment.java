package com.funini.pyoko;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.funini.pyoko.synth.Operator;

/**
 * Created by kei2s
 */
public class KeyboardFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    SeekBar mSeekBarFreq;
    Operator mOperator;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.keyboard, container, false);
    }

    SeekBar setupSeekBar(int rid){
        SeekBar ret = (SeekBar)getView().findViewById(rid);
        ret.setOnSeekBarChangeListener(this);
        return ret;
    }

    public void onStart() {
        super.onStart();
        mSeekBarFreq = setupSeekBar(R.id.sb_freq);
    }

    public void onProgressChanged(SeekBar seekBar,
                                  int progress, boolean fromUser) {
        float ratio = (float)progress / seekBar.getMax();
        float freq = percentToFreq(ratio);
        if(freq > Consts.SAMPLING_RATE){
            freq = Consts.SAMPLING_RATE;
        }
        //mTvFreq.setText("Freq: " + freq + " Hz");
        mOperator.setHz(freq);
    }

    private float percentToFreq(float ratio) {
        float ret = 440 * (float)Math.pow(8.0, (ratio - 0.5));
        return ret;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {

        mOperator.noteOn();
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        mOperator.noteOff();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof MainActivity)) {
            throw new UnsupportedOperationException(
                    "Unknown activity");
        } else {
            mOperator = ((MainActivity)context).getAlgorithm();
        }
    }
}
